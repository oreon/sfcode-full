#property copyright "Copyright © 2006, Neuraltraders corp"
#property link      "http://neuraltraders.info"

// Include Neural Network package
#include <ntann.mqh>


#include "include\NTCommons.mqh"

// Global defines
#define ANN_PATH	"C:\\ANN\\"
// EA Name
#define NAME		"CrystalBall"

//---- input parameters
extern double Lots = 0.1;
extern bool UseMoneyManagement = true;
extern double RiskFactor = 1;
int MAGIC_NUM = 34599;
extern int Stop = 250;
extern int Trail = 90;
extern int Key = 24000;
extern bool UseTrendFilter = false;

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
   ann =f2M_create_standard (4, 1, 3, 5, 1);
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
	//Print (text);
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
   //ArrInput[0] = open * 0.1;
   //ArrInput[1] = high * 0.1;
   //ArrInput[2] = low * 0.1;
   ArrInput[0] = close* 0.1;
  // return arr;
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
   if(!IsDemo()){
    if(  !validateKey(Key) ) {
      Comment( Key + " is not valid - please enter a valid license key .");
   }
  }


    modifyOrder(MAGIC_NUM, Trail);


    if(timeprev==Time[0] ){
       return(0);
     }
     timeprev=Time[0];
     
     
    for(int i = 1800 ; i > 2; i--){
      
      AnnTrain(Open[i], High[i], Low[i], Close[i], Close[i-1]);
    }
    
    CreateInputArr(Open[0], High[0], Low[0], Close[0]);
   //  CreateInputArr(Close[1]);
    
    int ret = f2M_run (ann, ArrInput);
   
    if (ret < 0) {
	     debug (0, "Network RUN ERROR! ann=" + ann);
	     return (FANN_DOUBLE_ERROR);
    }
    
    double out = 10 * f2M_get_output (ann, 0);
    int macd = macd();
    out = NormalizeDouble(out, 5);
    debug (3, "f2M_get_output(" + ann + ") returned: " + out);
    Comment("\n\n Next Predicted Close : " + out + " Trend" + macd );
    CreateArrow(out);
  
    double minDiff = 2 * Point;
    int ichi = ichi();
    
    if(out > Ask + minDiff ){
     
      if(!UseTrendFilter || (UseTrendFilter && ichi  > 0 ) ){
       closeOrders(OP_SELL);
      placeOrder(OP_BUY, out /*Ask + (out - Ask)/4*/);
      }
     }
      
    if (out < Bid - minDiff ){
      
        if(!UseTrendFilter || (UseTrendFilter && ichi < 0 ) ){
         closeOrders(OP_BUY);
      placeOrder(OP_SELL,  out /*Bid - (Bid - out)/4*/);
     }
    }      
   
   
    return (0);
}


void CreateArrow(double out){
     string oname = "target";
    int oindex = ObjectFind(oname);
    if(oindex == -1 )
      ObjectCreate(oname, OBJ_ARROW, 0, TimeCurrent(), out);
    else 
      ObjectMove(oname, 0, TimeCurrent(), out);
    

}

void placeOrder(int op, double tp){
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
      
      ticket=OrderSend (Symbol(),OP_BUY, lots,Ask,3,Bid - (Stop * Point) , tp,NAME + getTimeFrame(), MAGIC_NUM,0, Purple);
   }
   else if( op == OP_SELL ){
      ticket=OrderSend(Symbol(),OP_SELL,lots,Bid,3,Ask + (Stop * Point) , tp,NAME + getTimeFrame(), MAGIC_NUM,0, Yellow);
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
   

