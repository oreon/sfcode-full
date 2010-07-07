//+------------------------------------------------------------------+
//|                                              ytg_Speed_MA_ea.mq4 |
//|                                                     YURIY TOKMAN |
//|                                            yuriytokman@gmail.com |
//+------------------------------------------------------------------+
#property copyright "YURIY TOKMAN"
#property link      "yuriytokman@gmail.com"

#include <NTCommons.mqh>

extern int  PeriodMA     = 13;
extern int  Porog        = 10 ;
extern int  shift        = 1;
extern bool Revers       = false;
extern int  TP           = 1500;
extern int  SL           = 990;
extern int MagicNumber = 4567;
extern int Lots = 0.01;
extern string name = "Zoomer";

int prd = PERIOD_H1;
//+------------------------------------------------------------------+
int start()
  {
//----
   double MA_bar0 =iMA(NULL,0,PeriodMA,0,0,0,shift);
   double MA_bar1 =iMA(NULL,0,PeriodMA,0,0,0,shift+1);
   double Delta = MA_bar0-MA_bar1;   
   
   int orders = getOrderCount(MagicNumber);
   if(orders > 0 )
      TrailingStop(MagicNumber);
   
   int signal = signal();
   
   
   
    
   double sma = iMA(NULL,prd,50,0,MODE_SMA,PRICE_MEDIAN,0);
   //if(orders <1){
      double atr = 6 * getAtr(Symbol());
      double tp = 4 *  getAtr(Symbol());
      if(signal == 1) {
         closeOrdersOp(MagicNumber, OP_SELL); 
         if(orders == 0 )
            int ticket=sendOrderRel(OP_BUY,  Bid- atr *Point, Ask+tp*Point, Lots, name + getTimeFrame(), MagicNumber);
         
        
         
       }
      if(signal == -1){
         closeOrdersOp(MagicNumber, OP_BUY); 
         if(orders == 0 )
             ticket=sendOrderRel(OP_SELL,  Ask+atr*Point, Bid-tp*Point, Lots, name + getTimeFrame(), MagicNumber);
            
         
      }   
//----
   return(0);
  }
//+------------------------------------------------------------------+


int signal(){
  
   double ma = iMA(NULL,prd,20,0,MODE_EMA,PRICE_MEDIAN,0);
   double sma = iMA(NULL,prd,50,0,MODE_EMA,PRICE_MEDIAN,0);
   double lma = iMA(NULL,prd,100,0,MODE_EMA,PRICE_MEDIAN,0);
 //  int boll = boll(PERIOD_M30);
   
   if(ma > sma && Close[1] < ma && Close[0] > ma && sma > lma ) {
      return (1);
   }
   
    if(ma < sma && Close[1] > ma && Close[0] < ma && sma < lma ) {
      return (-1);
   }

   return (0);
}