//+------------------------------------------------------------------+
//|                                                    NTCommons.mq4 |
//|                            Copyright © 2009, NeuralTraders Corp. |
//|                                    http://www.neuraltraders.info |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2009, NeuralTraders Corp."
#property link      "http://www.neuraltraders.info"


bool validateKey(int key){
       int accno = AccountNumber();
       int tempkey = accno * 7 - 29;
       if(tempkey == key){
         return (true);
       }
      Comment("Your key is not valid - contact support@neuraltraders.info for a new license key");
      return (false);
}
/*
int ichi(){
  double ichi =  iIchimoku(NULL, PERIOD_H4, 9, 26, 52, MODE_KIJUNSEN, 1);
  if(ichi > Close[1]) return (-1);
  if(ichi < Close[1]) return (1);
  return (0);   
}*/

#import "kernel32.dll"
void GetLocalTime(int& TimeArray[]);
void GetSystemTime(int& TimeArray[]);
int GetTimeZoneInformation(int& TZInfoArray[]);
#import

int getTimeFrame(){
   return ( (Time[2] - Time[1])/60 );
}

double getLots(double risk){
   double lots = risk * (AccountEquity()/10000.0);
   if(lots < 0.01) lots =0.01;
   return (lots);
}

int getGmtShift(){
   int TimeArray[4];
   int TZInfoArray[43];
   int gmt_shift=0;
   int ret=GetTimeZoneInformation(TZInfoArray);
   if(ret!=0) gmt_shift=TZInfoArray[0];
   //Comment("Difference between your local time and GMT is: ",(gmt_shift/60)," hours \n");
   if(ret==2) gmt_shift+=TZInfoArray[42];
  // Comment("Current difference between your local time and GMT is: ",(gmt_shift/60)," hours. ", TZInfoArray[42]);
   return (gmt_shift/60);
}

int getGmtHour(){
   int local = TimeHour(TimeLocal());
   return ( local + getGmtShift() );  
}

int getOverBoughtOrSold(){
   double wpr = iWPR(NULL,0,14,0);
   if(wpr <= -90 )
      return (-1);
   if(wpr > -10)
      return (1);
     
   return (0);

}



int getOrderCount(int num){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
  
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)      
         continue;
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol() )
         ordersForThisEA++;
   }
  
   return ( ordersForThisEA );
}



void modifyOrder(int MAGIC_NUM, int TrailingStop){

   if(!TrailingStop)
      return;
     
   int total  = OrdersTotal();
   double dblTrailingStop = Point*TrailingStop;
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
      if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol()) {
     
       if(OrderType()==OP_SELL){
      
         if((OrderOpenPrice()-Ask)> dblTrailingStop){
         if((OrderStopLoss()>(Ask+ dblTrailingStop)) || (OrderStopLoss()==0))
           {
            OrderModify( OrderTicket(),OrderOpenPrice(),  Ask + dblTrailingStop,OrderTakeProfit(),0,Red);
            return(0);
           }
         }
        }
       
        if(OrderType()==OP_BUY){
       
           if(Bid-OrderOpenPrice()>dblTrailingStop)
           {
          
            if(OrderStopLoss()<Bid-dblTrailingStop)
              {
               OrderModify(OrderTicket(),OrderOpenPrice(),Bid- dblTrailingStop,OrderTakeProfit(),0,Green);
               return(0);
              }
           }
        }
       }
      
     }

}












//////////////////////////////// Indicator Functions //////////////////////////////////////////////

int ichi(){
  int prd = PERIOD_D1;
  double buyt =  iIchimoku(NULL, prd , 9, 26, 52, MODE_TENKANSEN, 1);
  double sellk =  iIchimoku(NULL, prd, 9, 26, 52, MODE_KIJUNSEN, 1);
  if(buyt > sellk) return (1);
  if(sellk > buyt) return (-1);
  return (0);   

}

int bollinger(){
   if (iBands(NULL,0,20,2,0,PRICE_MEDIAN,MODE_LOWER,0) > Low[0] ) return (1);
   if (iBands(NULL,0,20,2,0,PRICE_MEDIAN,MODE_UPPER,0) < High[0] ) return (-1);
   return (0);
}

