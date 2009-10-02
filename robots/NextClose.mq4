//+------------------------------------------------------------------+
//|                                                    NeuroMACD.mq4 |
//|                                                 Mariusz Woloszyn |
//|                                           Fann2MQL.wordpress.com |
//+------------------------------------------------------------------+
#property copyright "Mariusz Woloszyn"
#property link      "Fann2MQL.wordpress.com"

// Include Neural Network package
#include <ntann.mqh>


#include "include\NTCommons.mqh"

// Global defines
#define ANN_PATH	"C:\\ANN\\"
// EA Name
#define NAME		"NeuroMACD"

//---- input parameters
extern double Lots = 0.01;
extern bool UseMoneyManagement = true;
extern double RiskFactor = 1;
int MAGIC_NUM = 345992;
extern int Stop = 250;
extern int Trail = 90;

// Global variables

// Long position ticket
int LongTicket = -1;

int DebugLevel = 0;

// Short position ticket
int ShortTicket = -1;

// Remembered long and short network inputs
double LongInput[];
double ShortInput[];

double ArrInput[4];

int  ann;
datetime timeprev=0;
double mult = 0.1;

extern bool TimeFilter = true;

extern bool CloseAtPeriodEnd = false;
extern bool TradeMultipleInSameTimeFrame = true;
extern int BeginHour = 22;
extern int EndHour = 7;
//+------------------------------------------------------------------+
//| expert initialization function                                   |
//+------------------------------------------------------------------+

int init ()
{
    
    ann = CreateAnn();
    f2M_parallel_init();
    
     double price = Close[1];
   if(price < 1 ) mult = 1; 
   if(price < 10 ) mult = 0.1;
   if (price > 10 && price < 100 ) mult =0.01;
   if (price > 100 && price < 1000 ) mult =0.001;
    
    return (0);
}


int CreateAnn(){
   ann =f2M_create_standard (4, 4, 6, 5, 1);
	f2M_set_act_function_hidden (ann, FANN_SIGMOID_SYMMETRIC_STEPWISE);
	f2M_set_act_function_output (ann, FANN_SIGMOID_SYMMETRIC_STEPWISE);
	f2M_randomize_weights (ann, -0.4, 0.4);
	debug (1,"ANN: '" +  "' created successfully with handler " +ann);
    
    if (ann == -1) {
	debug (0, "ERROR INITIALIZING NETWORK!");
    }
    return (ann);

}



void
debug (int level, string text)
{


  //  if (DebugLevel >= level) {
	if (level == 0)
	    text = "ERROR: " + text;
	Print (text);
    //}
    
  
}






void ann_destroy (){
    int ret = -1;
    ret = f2M_destroy (ann);
    debug (1, "f2M_destroy(" + ann + ") returned: " + ret);
}


void AnnTrain(double open, double high, double low, double close, double result){
   
   CreateInputArr(open, high, low, close);
   double  resultArr[1];
   resultArr[0] = result * mult;
   
   //dumpInput();   
    if (f2M_train (ann, ArrInput, resultArr) == -1) {
	  debug (0, "Network TRAIN ERROR! ann=" + ann);
    }
    //debug (3, "ann_train(" + ann + ") succeded");
}

void CreateInputArr(double open, double high, double low, double close){
   //double arr[4];
   
   ArrInput[0] = open * mult;
   ArrInput[1] = high * mult;
   ArrInput[2] = low * mult;
   ArrInput[3] = close* mult;
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
int start ()
{
    modifyOrder(MAGIC_NUM, Trail);
    int orders = getOrderCount(MAGIC_NUM);

    if(timeprev==Time[0] )
     {
      if(orders > 0 )
       return(0);
     }
   timeprev=Time[0];
   
   int hour = TimeHour(TimeCurrent());
   
 /*  
 if(TimeFilter && total == 0 ){
      int hr = TimeHour(TimeCurrent());
      if(hr <= EndHour ) hr += 24;
 
      if(hr == BeginHour - 2 &  CloseAtPeriodEnd){
         Print ("hour is " + 7 + " closing all ");
         //CloseThisSymbolAll();
       }
 
      if(! ( hr >= BeginHour && hr < 24 + EndHour)){
          Comment( "Now is " +  hr + ":00  Trading will begin at " + BeginHour + ":00 \n");
          return(0);
      }else{
          Comment( "Ready to trade \n");
      }
     
   }*/ 

    for(int i = 900 ; i > 2; i--){
      
      AnnTrain(Open[i], High[i], Low[i], Close[i], Close[i-1]);
    }
    
    CreateInputArr(Open[0], High[0], Low[0], Close[0]);
    
    int ret = f2M_run (ann, ArrInput);
    
    if (ret < 0) {
	     debug (0, "Network RUN ERROR! ann=" + ann);
	     return (FANN_DOUBLE_ERROR);
    }
    
    double out = (1.0/mult) * f2M_get_output (ann, 0);
    double mse = f2M_get_MSE(ann);
    Comment("Next Close " + out + " mse " + mse);
   // debug (3, "f2M_get_output(" + ann + ") returned: " + out);
    
    if(orders == 0 ) {
      placeOrders(out);
     } else{
         manageOrders(out);         
     }    
      
    return (0);
}


void manageOrders(double out){
   int total  = OrdersTotal();
    for(int cnt=0;cnt<total;cnt++)
     {
       if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
       if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol()) {
         double tp = OrderTakeProfit();
         if(OrderType() == OP_BUY ) {
         
            if(  out > tp ) 
               changeTP(MAGIC_NUM, out);
            else {
               if(OrderProfit() > 0 ){
                  OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
                  // timeprev = 0;  
               }
            }
            
         }
         
          if(OrderType() == OP_SELL ) {
         
            if(  out < tp ) 
               changeTP(MAGIC_NUM, out);
             else {
              // if(out > OrderOpenPrice() )
              if(OrderProfit() > 0 ){
                  OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
                  // timeprev = 0;
               }
            }
            
         }
       }
    }
 }
     


string name = "NHERO";

void placeOrders(double out){
    double minDiff = 1 * Point;
    //int macd = macd();
    
    double lots = Lots;
   
   if(UseMoneyManagement){
      lots = getLots(RiskFactor);
   }
    
    int obv = obv(PERIOD_H4);
    
    if(out > Ask + minDiff && obv > 0 ){
     closeOrders(OP_SELL);
     placeOrderL(OP_BUY,  out ); //, Stop, lots, name );
     }
      
    if (out < Bid - minDiff && obv < 0 ){
       closeOrders(OP_BUY);
      
       placeOrderL(OP_SELL,  out );
      //placeOrder(OP_SELL, MAGIC_NUM, out, Stop, lots, name);
    }      
 

}


void placeOrderL(int op, double tp){
   int ticket = 0;
   int ticket2 = 0;
   int totalOrders = getOrderCount(MAGIC_NUM);
   double lots = Lots;
   
   if(UseMoneyManagement){
      lots = getLots(RiskFactor);
   }
  
   if( totalOrders > 0 ){
      return(0);
   }
   
   if(op == OP_BUY ){
      
      ticket=OrderSend (Symbol(),OP_BUY, lots,Ask,3,Bid - (Stop * Point) , tp,"NHERO " + getTimeFrame(), MAGIC_NUM,0, Purple);
   }
   else if( op == OP_SELL ){
      ticket=OrderSend(Symbol(),OP_SELL,lots,Bid,3,Ask + (Stop * Point) , tp,"NHERO " + getTimeFrame(), MAGIC_NUM,0, Yellow);
   }
   
}




void dumpInput(){
   Print("  " + ArrInput[0] + " " + ArrInput[1] + " " + ArrInput[2] + " " + ArrInput[3] );
}

