//+------------------------------------------------------------------+
//|                                                    NTCommons.mq4 |
//|                            Copyright © 2009, NeuralTraders Corp. |
//|                                    http://www.neuraltraders.info |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2009, NeuralTraders Corp."
#property link      "http://www.neuraltraders.info"

#include <stdlib.mqh>
#include <WinUser32.mqh>


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


void placeOrder(int op, int MAGIC_NUM, int Limit, int Stop, double Lots, string name){
   int ticket = 0;
   int ticket2 = 0;
   int totalOrders = getOrderCount(MAGIC_NUM);
  
    if( totalOrders > 0 ){
     // Print ("already
      return(0);
   }
 
  //double atr = iATR(NULL, PERIOD_H1, 14, 0 )* 10;
   //Print("atr " + atr);
    if(op == OP_BUY ){
      double sl = Bid - (Stop * Point);
      double tp = Ask+ ( Limit*Point);
      ticket=OrderSend (Symbol(),OP_BUY, Lots,Ask,3, sl, tp,name + getTimeFrame(), MAGIC_NUM,0, Purple);
      if(ticket<1)
     {
      int error=GetLastError();
      Print("Error = ",ErrorDescription(error));
      }
   }
   else if( op == OP_SELL ){
      tp = Bid - ((Limit * Point));
      sl = Ask+ (( Stop*Point) );
      ticket=OrderSend(Symbol(),OP_SELL,Lots,Bid,3,sl ,tp,name + getTimeFrame(), MAGIC_NUM,0, Yellow);
      
       if(ticket<1)
     {
      error=GetLastError();
      Print("Error = ",ErrorDescription(error));
      }
 
   }
}




	void changeTP(int MAGIC_NUM, double tp){

	   int total  = OrdersTotal();
	   for(int cnt=0;cnt<total;cnt++)
	    {
	      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
	      if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol()) {
            OrderModify(OrderTicket(),OrderOpenPrice(), OrderStopLoss(), tp,0,Green);
		       return(0);

         }
      }
}


void closeOrders(int MagicNumber){
  int total  = OrdersTotal();
  
   double price = 0;
   
   datetime orderMaxDuration = 290000; 
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)    continue;
     
      if( OrderSymbol() == Symbol() && OrderMagicNumber()== MagicNumber  ) {
          
         switch(OrderType()){
            case OP_BUYLIMIT :
            case OP_BUYSTOP  :
            case OP_SELLLIMIT:
            case OP_SELLSTOP :
               OrderDelete(OrderTicket());
         }  
             
            if( OrderType() == OP_SELL ) price = Ask; else price = Bid;
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
         
       }
   }
}


void closeOrdersOp(int MagicNumber, int op){
  int total  = OrdersTotal();
  
   double price = 0;
   
   datetime orderMaxDuration = 290000; 
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)    continue;
     
      if( OrderSymbol() == Symbol() && OrderMagicNumber()== MagicNumber && OrderType() == op ) {
          
         switch(OrderType()){
            case OP_BUYLIMIT :
            case OP_BUYSTOP  :
            case OP_SELLLIMIT:
            case OP_SELLSTOP :
               OrderDelete(OrderTicket());
         }  
             
            if( OrderType() == OP_SELL ) price = Ask; else price = Bid;
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
         
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

