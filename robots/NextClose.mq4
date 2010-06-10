//+------------------------------------------------------------------+
//|                                    
//+------------------------------------------------------------------+
#property copyright "NeuralTraders.info"
#property link      "NeuralTraders.info "

// Include Neural Network package
#include <ntann.mqh>


#include "include\NTCommons.mqh"

// Global defines
#define ANN_PATH	"C:\\ANN\\"
// EA Name
#define NAME		"NextClose-V14-"

//---- input parameters
extern double Lots = 0.01;
extern bool UseMoneyManagement = true;
extern double RiskFactor = 0.05;
extern int MAGIC_NUM = 34777;
extern int Stop = 140;
extern int Trail = 15;
extern int TakeProfit = 10;
extern bool UseFixedTP = true;
extern bool TrailFractal = false;
extern bool ContinuousMode = false;
//extern bool StepOrders = false;
extern int RiskReducer = 20;
extern int Epochs = 500;
//extern int TakeProfit = 55;

// Global variables
#define SLD_WND 24

// Long position ticket
int LongTicket = -1;

int DebugLevel = 0;
// Short position ticket
int ShortTicket = -1;

// Remembered long and short network inputs
double LongInput[];
double ShortInput[];

double ArrInput[SLD_WND];

int  ann;
/*extern*/ int BarsForSL = 0;
datetime timeprev=0;
double mult = 0.1;
int Prd = 4800;

//extern int GMTOffset = 1;
int MAX_ORDERS = 2;


int timeSigPrev = -1;
string path;

extern double MinMSE = 0.00000250;
extern bool TimeFilter = true;
extern int SlidingWindow = 16;

extern int CloseAfter = 1;
extern int BeginHour = 17;
extern int EndHour = 4;
extern int Threshhold = 8;
extern int Offset = 2;
/*extern*/ int Step = 20;
extern bool AutoAdjustDigits = true;

int cnt = 0;
bool tradedInThisPrd = false;


string name = "NEXTCLOSE-V15-";

//+------------------------------------------------------------------+
//| expert initialization function                                   |
//+------------------------------------------------------------------+

string slcmt = "SL set to low of 10 bars";
string comment = "";

int init ()
{
     ArrayResize(ArrInput, SlidingWindow);
   
     if(!IsTesting()){
       Offset =  TimeHour(TimeCurrent()) - getGmtHour() ;
     }   
    
    int day = TimeDayOfWeek(TimeGmt() );
  
  if(day == 0  ){  
   Comment("Trading disabled on sunday .");
   return (0);
  }
    
    path = TerminalPath() + "//experts//files"; 
    ann = CreateAnn();
    f2M_parallel_init();
    
     double price = Close[1];
     
    if(AutoAdjustDigits && Digits == 3 || Digits ==5 ){
   
      TakeProfit= TakeProfit * 10; // ������� ������� � ������ �� ���� ��������.
      //PipStep=PipStep * 10; // ��������� � ������ ������ �� ������� ������������ ��������� ����� ������.
      Trail = Trail*10;
      Stop = Stop *10;
      Step = Step * 10;
      Threshhold = Threshhold * 10;
      //RiskReducer = RiskReducer * 10;
   }
     /*
     for(int i = 0; i < Digits; i++){
         mult = mult * .01;
     }*/
   if(price < 1 ) mult = 1; 
   if(price < 10 ) mult = 0.1;
   if (price > 10 && price < 100 ) mult =0.01;
   if (price > 100 && price < 1000 ) mult =0.001;
    
    return (0);
}


int start ()
{
   int ret = perform();
   Comment(comment);
   return (ret);
}


int perform ()
{
   comment = "---" + name +   "--\n";
   // modifyOrder(MAGIC_NUM, Trail);
    int orders = getOrderCount(MAGIC_NUM);
    int j = 0;
    
    if(orders > 0 ){
    
      double force = iForce(NULL, 0, 6,MODE_SMA,PRICE_CLOSE,0);
   
      if(MathAbs(force) > 1){
       // closeOrders(MAGIC_NUM);
      }
      
      if(TrailFractal)  
        TrailingStop(MAGIC_NUM);
      else  {       
        double out = (1.0/mult) * f2M_get_output (ann, 0);
        //setMissingTP(MAGIC_NUM, out, out);
        modifyOrder(MAGIC_NUM, Trail);
      }
   
   }
   
   
   int day = TimeDayOfWeek(TimeGmt() );
   
   if(!doTimeFilter(day))
      return (0) ;

   int  timeSigNow = TimeMinute(Time[0])/5;
   
   if(timeprev != Time[0] ){
      tradedInThisPrd = false;
   }
   

    if(timeprev==Time[0] )
     {
      if(!ContinuousMode && tradedInThisPrd)  
       return(0);
     }else{
        trainNN();
     }
     
     timeprev=Time[0];
     
  
   timeSigPrev = timeSigNow;
  
     for( j= 0; j < SlidingWindow; j++){
         ArrInput[j] = Close[SlidingWindow - j];
     }
    
    int ret = f2M_run (ann, ArrInput);
    
    // setTP(MAGIC_NUM, ret, ret) ;
    
    if (ret < 0) {
	     debug (0, "Network RUN ERROR! ann=" + ann);
	     return (FANN_DOUBLE_ERROR);
    }
    
    out = (1.0/mult) * f2M_get_output (ann, 0);
    out = NormalizeDouble(out, Digits);
    double mse = f2M_get_MSE(ann);
    
    if(mse >= 0  ){
       setMissingTP(MAGIC_NUM, out, out);
       comment = StringConcatenate( comment,  
       "\n Spread :" + getSpreadStr() + "\n SL :" + slcmt +
       "\nNext Target: " + DoubleToStr(out, Digits) + " mse " + mse);
    } 
    
    ObjectDelete("text_object");
    ObjectCreate("text_object", OBJ_HLINE, 0, TimeCurrent(), out);
    
    
   if(orders < MAX_ORDERS) {
      //if(UseFixedTP)
     //    placeOrders(TakeProfit);
     // else
      placeOrders(out);
    } else{ 
      //changeTP(MAGIC_NUM, out );
      Print("managing order");
      manageOrders(out);         
     }    
    
    //setTP(MAGIC_NUM, out, out);
    
   
    return (0);
}






int CreateAnn(){
   ann =f2M_create_standard (4, SlidingWindow, 12, 18, 1);
	f2M_set_act_function_hidden (ann, FANN_SIGMOID_SYMMETRIC_STEPWISE);
	f2M_set_act_function_output (ann, FANN_SIGMOID_SYMMETRIC_STEPWISE);
	f2M_randomize_weights (ann, -0.4, 0.4);
	debug (1,"ANN: '" +  "' created successfully with handler " +ann);
    
    if (ann == -1) {
	debug (0, "ERROR INITIALIZING NETWORK!");
    }
    return (ann);

}

int trainNN(){
  
   double mset = 1;
   
   while(mset > MinMSE || cnt < Epochs ){
      for(int i = Prd ; i >= (SlidingWindow + 1); i--){
         for(int j= 0; j < SlidingWindow; j++){
            ArrInput[j] = Close[i - j];
         }
         AnnTrain( Close[i - j -1]);
      }
      int re2t = f2M_run (ann, ArrInput);
      mset = f2M_get_MSE(ann);
      cnt++;
      
      Comment("Training neural net ....: MSE:  " +mset + ":" + cnt);
   }
   //f2M_save(ann, path);

}



void debug (int level, string text){
  //  if (DebugLevel >= level) {
	if (level == 0)
	    text = "ERROR: " + text;
	Print (text);  
}



void AnnTrain( double result){
  
   double  resultArr[1];
   resultArr[0] = result * mult;
   
   //dumpInput();   
    if (f2M_train (ann, ArrInput, resultArr) == -1) {
	  debug (0, "Network TRAIN ERROR! ann=" + ann);
    }
    //debug (3, "ann_train(" + ann + ") succeded");
}

void CreateInputArr(double open, double close, double volume){
   //double arr[4];
   
   ArrInput[0] = open * mult;
   //ArrInput[1] = high * mult;
   //ArrInput[2] = low * mult;
   ArrInput[1] = close* mult;
   ArrInput[2] = volume ; //* mult;
  // return arr;
}


int obv (int prd ){
   double one = iOBV(NULL, prd, PRICE_CLOSE, 1);
   double two=iOBV(NULL, prd, PRICE_CLOSE, 2);
   double three=iOBV(NULL, prd, PRICE_CLOSE, 3);
   
  if(one > two && two > three ) return (1);
   if(one < two && two < three ) return (-1);    
   return (0);
   
}

 


//+------------------------------------------------------------------+
//| expert deinitialization function                                 |
//+------------------------------------------------------------------+
int
deinit ()
{
	ann_destroy ();   
    // Deinitialize Intel TBB threads
    f2M_parallel_deinit ();

    return (0);
}


//+------------------------------------------------------------------+
//| expert start function                                            |
//+------------------------------------------------------------------+


void manageOrders(double out){
   int total  = OrdersTotal();
    for(int cnt=0;cnt<total;cnt++)
     {
       if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
       if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol()) {
         double tp = OrderTakeProfit();
         
       
         //continue;
         double minStep = 30*Point;
         
         if(OrderType() == OP_BUY ) {
         
            //if (Close[0] == out) OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
         
            if( out < OrderOpenPrice() - minStep    ){
               OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
                Sleep(20000);
            }else if (out > OrderOpenPrice() && out < Close[0] && OrderProfit() > 0 ){
              // OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
            }else changeTP(MAGIC_NUM, out );
               
            
            /*
            if(  out != OrderTakeProfit() && out > OrderOpenPrice() ) {
              changeTPop(MAGIC_NUM, out, OP_SELL);
             }
             if(out < OrderOpenPrice()   ){
                OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
            }*/
            /*
            else {
              // if(OrderProfit() > 0 ){
                  OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
                  // timeprev = 0;  
               //}
            }*/
            
         }
         
          if(OrderType() == OP_SELL ) {
         
          //  if (Close[0] == out) OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
         
           if( out > OrderOpenPrice() + minStep  ){
                OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
                Sleep(20000);
            }else if (out < OrderOpenPrice() && out > Close[0]  && OrderProfit() > 0 ){
              // OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
            }else changeTP(MAGIC_NUM, out );
            
         }
       }
    }
 }
     



void placeOrders(double out){
    double threshhold = Threshhold * Point;
    
    
    int bo = getOrderCount(MAGIC_NUM);
    //int so = getOrderCountOp(MAGIC_NUM, OP_SELL);
    double ma = 0;
    
    if(Close[3] > Close[2]  && Close[2] > Close[1]) 
      ma = -1;
    if(Close[3] < Close[2]  && Close[2] < Close[1]) 
      ma = 1;
    ma = sar();
      
      
   int boll = boll();
    
    
    Print(out + " "  + (Close + threshhold));
    
    
    if(out > Ask + threshhold  && boll >= 0  ){
      closeOrdersOp(MAGIC_NUM, OP_SELL);
      //out = Ask + TakeProfit * Point;
     placeOrderL(OP_BUY,  out ); //, Stop, lots, name );
    }
    
   
      
    if (out < Bid - threshhold && boll <= 0 /*&& out < ma&& obv < 0 && bo == 0*/ ){
      Print("Placing sell order " + Symbol());
      closeOrdersOp(MAGIC_NUM, OP_BUY);
    //  out = Bid - TakeProfit * Point;
       placeOrderL(OP_SELL,  out );
      //placeOrder(OP_SELL, MAGIC_NUM, out, Stop, lots, name);
    }      
 

}


void placeOrderL(int op, double tp){
   int ticket = 0;
   int ticket2 = 0;
   int totalOrders = getOrderCount(MAGIC_NUM);
    double lots = Lots;
    
    tradedInThisPrd = true;
   
   if(UseMoneyManagement){
      lots = getLots(RiskFactor, UseMoneyManagement, Lots);
   }
  
   if( totalOrders > 0 ){
      //Print ("Not placing as there are " + totalOrders);
      return(0);
   }
   
   double atr = getAtr(Symbol());
   
   if(op == OP_BUY ){
      tp  = MathMax(tp, Ask + (atr * Point) );
      ticket=OrderSend (Symbol(),OP_BUY, lots,Ask,3,0 , 0,name + getTimeFrame(), MAGIC_NUM,0, Purple);
      double sl = Low[iLowest(NULL,0,MODE_LOW,BarsForSL,0)];
      double minsl = Bid - (Stop * Point);
      if ( sl < minsl || BarsForSL == 0) { sl = minsl; slcmt = "SL set to min SL: " + Stop ; }
      //setTP(MAGIC_NUM, Ask + TakeProfit * Point/*tp*/, Ask + TakeProfit * Point);
      setSL(MAGIC_NUM, sl);
     changeTP(MAGIC_NUM, tp);
   }
   else if( op == OP_SELL ){
        tp  = MathMax(tp, Bid - (atr * Point) );
       ticket=OrderSend(Symbol(),OP_SELL,lots,Bid,3,0 , 0,name+ getTimeFrame(), MAGIC_NUM,0, Yellow);
       sl = High[iHighest(NULL,0,MODE_HIGH,BarsForSL,0)];
       //tp = Bid - TakeProfit * Point;
      // setTP(MAGIC_NUM, tp, Bid - TakeProfit * Point);
        minsl = Ask + (Stop * Point);
       if ( sl > minsl   || BarsForSL == 0) { sl = minsl; slcmt = "SL set to min SL: " + Stop ; }
       setSL(MAGIC_NUM, sl);
       changeTP(MAGIC_NUM, tp);
   }
    
 
   
}


int setTP(int num, double tp, double fbtp){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
   
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol() &&  MathAbs(  OrderOpenPrice() - tp ) > Threshhold * Point  && OrderTakeProfit() == 0 ){ 
      
        //if( (OrderType() == OP_SELL && tp < OrderOpenPrice()) || (OrderType() == OP_BUY && tp > OrderOpenPrice()))
         if(!OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),tp,0,Green)){
            //if(orderType == OP_SELL)
               OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),fbtp,0,Green);
               
           // if(orderType == OP_BUY)
           //   OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),fbtp,0,Green)   
         }
      }
   }
   
   return ( ordersForThisEA );
}



int setMissingTP(int num, double tp, double fbtp){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol()  && OrderTakeProfit() == 0  || MathAbs( OrderTakeProfit() -OrderOpenPrice()) > 0.3 ){ 
         if(OrderType() == OP_BUY)
           OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),Ask + TakeProfit * Point,0,Green);
               
         if(OrderType() == OP_SELL)
           OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),Bid - TakeProfit * Point,0,Green) ;
        
      }
   }
   
   return ( ordersForThisEA );
}





int setSL(int num, double sl){
    int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
      
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol()  && OrderStopLoss() == 0){ 
      
         OrderModify(OrderTicket(),OrderOpenPrice(), sl ,OrderTakeProfit(),0,Green);
         
      }
   }

}


void placeBigOrder(int op, double tp,  double lots){

      int ticket = 0;
      //int step = 10;
      int count = MathAbs((Ask - tp)) / (Step * Point);
      Print("count is " + count);
      if(op== OP_BUY ){
         for(int i = 1; i < count; i++){
             ticket=OrderSend (Symbol(),OP_BUYSTOP, lots,Ask + (i *Step)* Point,3,Bid - (Stop * Point) , tp,name + getTimeFrame(), 
             MAGIC_NUM, /*iTime( Symbol(), PERIOD_D1, 0 ) + 86400*/0, Purple);
         }
       }
      else{ 
         //Print("placing sell order at " + (Bid - profitTriggerForBig));
         for(i = 1; i < count; i++){
             ticket=OrderSend(Symbol(),OP_SELLSTOP,lots,Bid - (i * Step ) * Point,3,Ask + (Stop * Point) , tp,name + getTimeFrame(), 
             MAGIC_NUM,/*iTime( Symbol(), PERIOD_D1, 0 ) + 86400*/ 0, Yellow);
         }
      }

}



void dumpInput(){
   Print("  " + ArrInput[0] + " " + ArrInput[1] + " " + ArrInput[2] + " " + ArrInput[3] );
}

string timeText =  "  Trading will begin at GMT:";
 
int doTimeFilter(int day){
   int endHour = EndHour;
   if(day == 5 ){
      endHour = 20;
   }
   int trange = tradeRange(BeginHour, endHour, CloseAfter, Offset);
   if(!TimeFilter){
       printComment(" TimFilter off ");
       return (1);
   }
   string timeText =  "\nLots:" +  Lots + " "; 
   int ret = 0;
        
   if(trange == TRADING ){
      timeText = " \nReady to trade \n Trade Range " + BeginHour + ":00 - " + EndHour + ":00 GMT ";
      ret = 1;
   }else if (trange == CLOSEHR){
      if (IsTesting()) int oset = Offset ; else oset = 0;
      int hour = TimeHour(getGmtHour(oset));
     
      timeText = hour + " Closing all open trades -  Trading will begin at GMT:";   
      timeText = timeText + "\n" +  (BeginHour) + ":00 and continue until " + EndHour + ":00";
      closeOrders(MAGIC_NUM);
   }else if (trange == POSTEND ){
      timeText = "\nRedcuing TP by " + RiskReducer ;
      reduceProfit(MAGIC_NUM, RiskReducer);
   }else {
      timeText = "  Trading will begin at GMT:";   
      timeText = timeText + "\n" +  (BeginHour) + ":00 and continue until " + EndHour + ":00";
     
   }
   
    printComment(timeText);
   
   return (ret) ;
 }

 void printComment(string cmt){
    
    comment = StringConcatenate( comment,  "Traded:" +  tradedInThisPrd + "\nNow is GMT : " +  getGmtHour() + " \n" + cmt 
         + " \n \n GMT Offset: " + Offset  );
         
   // double later = NormalizeDouble(iCustom (NULL, 0, "PredictedMA", 0, -2), MarketInfo(Symbol(), MODE_DIGITS) ); 
    
}  


void ann_destroy (){
    int ret = -1;
    ret = f2M_destroy (ann);
    debug (1, "f2M_destroy(" + ann + ") returned: " + ret);
}