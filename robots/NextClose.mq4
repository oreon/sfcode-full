//+------------------------------------------------------------------+
//|                                                    NeuroMACD.mq4 |
//|                                                 Mariusz Woloszyn |
//|                                           Fann2MQL.wordpress.com |
//+------------------------------------------------------------------+
#property copyright "Mariusz Woloszyn"
#property link      "Fann2MQL.wordpress.com"

// Include Neural Network package
#include <Fann2MQL.mqh>


#include "include\NTCommons.mqh"

// Global defines
#define ANN_PATH	"C:\\ANN\\"
// EA Name
#define NAME		"NeuroMACD"

//---- input parameters
extern double Lots = 0.1;
extern bool UseMoneyManagement = true;
extern double RiskFactor = 1;
int MAGIC_NUM = 34599;
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

//+------------------------------------------------------------------+
//| expert initialization function                                   |
//+------------------------------------------------------------------+

int init ()
{
    
    ann = CreateAnn();
    f2M_parallel_init();

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
   resultArr[0] = result * 0.1;
   
   //dumpInput();   
    if (f2M_train (ann, ArrInput, resultArr) == -1) {
	  debug (0, "Network TRAIN ERROR! ann=" + ann);
    }
    //debug (3, "ann_train(" + ann + ") succeded");
}

void CreateInputArr(double open, double high, double low, double close){
   //double arr[4];
   ArrInput[0] = open * 0.1;
   ArrInput[1] = high * 0.1;
   ArrInput[2] = low * 0.1;
   ArrInput[3] = close* 0.1;
  // return arr;
}




//+------------------------------------------------------------------+
//| expert deinitialization function                                 |
//+------------------------------------------------------------------+
int
deinit ()
{
    int i;
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
      //if(orders > 0 )
       return(0);
     }
   timeprev=Time[0];
   
   int hour = TimeHour(TimeCurrent());
 //  if(hour > 15 && hour < 22)
 //     return;

    for(int i = 900 ; i > 2; i--){
      
      AnnTrain(Open[i], High[i], Low[i], Close[i], Close[i-1]);
    }
    
    CreateInputArr(Open[0], High[0], Low[0], Close[0]);
    
    int ret = f2M_run (ann, ArrInput);
    
    if (ret < 0) {
	     debug (0, "Network RUN ERROR! ann=" + ann);
	     return (FANN_DOUBLE_ERROR);
    }
    
    double out = 10 * f2M_get_output (ann, 0);
    double mse = f2M_get_MSE(ann);
    Comment("Next Close " + out + " mse " + mse);
    debug (3, "f2M_get_output(" + ann + ") returned: " + out);
    
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
               if(OrderProfit() > 0 )
                  OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
            }
            
         }
         
          if(OrderType() == OP_SELL ) {
         
            if(  out < tp ) 
               changeTP(MAGIC_NUM, out);
             else {
               if(OrderProfit() > 0 )
                  OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
            }
            
         }
       }
    }
 }
     




void placeOrders(double out){
      double minDiff = 3 * Point;
    int macd = macd();
    if(out > Ask + minDiff ){
    //  closeOrders(OP_SELL);
      placeOrderL(OP_BUY, out);
     }
      
    if (out < Bid - minDiff  ){
      // closeOrders(OP_BUY);
      placeOrderL(OP_SELL, out);
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


void closeOrders(int op){
   int total  = OrdersTotal();
   
   Print("Closing order " + op );
   
   double price = 0;
   if( op == OP_SELL ) price = Ask; else price = Bid;
   datetime orderMaxDuration = 290000; 
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)    continue;
     
      if( OrderSymbol() == Symbol() && OrderMagicNumber()== MAGIC_NUM  ) {
          
         Print ("ordertimes " + OrderTicket() + " " + (TimeCurrent() -   OrderOpenTime()) + " " + orderMaxDuration);   
             
         if(  ( TimeCurrent()-OrderOpenTime())  > orderMaxDuration){
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
         }   

     
         if(OrderType() == op  ){
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
            continue;
          }
       }
   }
}

void dumpInput(){
   Print("  " + ArrInput[0] + " " + ArrInput[1] + " " + ArrInput[2] + " " + ArrInput[3] );
}

double macd()
  {
  
double dMacd1 = 12;
double dMacd2 = 26;
double dMacd3 =9;
   
   int period = 0;
   
   /*
    double one = iCustom(NULL, period , "ZeroLag MACD",0,0);
    double two = iCustom(NULL, period, "ZeroLag MACD",0,1);
    double three = iCustom(NULL, period, "ZeroLag MACD",0,2);
   */
   double one = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,1);
   double two = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,2);
   double three = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,3);
   
   double stoch = iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_MAIN,0);
   int stochsig = 0;
   if(stoch  > iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_SIGNAL,0) && stoch < 90)
    stochsig = 1;
   if(stoch  < iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_SIGNAL,0) && stoch > 10)    
    stochsig = -1;
    
   int obs = 0; //getOverBoughtOrSold();
  // if(obs != 0 )
  //    return (0);
  
   double wpr = iWPR(NULL,0,12,0);
   double wpr2 = iWPR(NULL,0,12,1);
   
   if(one > two  && obs != 1 ) return (1);
   else if (two > one  && obs != -1 ) return (-1);
   
   return (0);
 }
   
