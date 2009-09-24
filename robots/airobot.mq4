//+------------------------------------------------------------------+
//|                                       ArtificialIntelligence.mq4 |
//|                               Copyright © 2006, Yury V. Reshetov |
//|                                         http://reshetov.xnet.uz/ |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2006, Neuraltraders corp"
#property link      "http://neuraltraders.info"
//---- input parameters
extern int    x1 = 149;
extern int    x2 = 28;
extern int    x3 = 100;
extern int    x4 = 192;
// StopLoss level
extern double sl = 220;
extern double TrailingStop = 25;
extern double lots = 0.1;
extern int MagicNumber = 888;
static int prevtime = 0;
extern bool CloseLosers = true;

extern int Limit = 90;

//+------------------------------------------------------------------+
//| expert initialization function                                   |
//+------------------------------------------------------------------+
int init()
  {
//----
   return(0);
  }
//+------------------------------------------------------------------+
//| expert deinitialization function                                 |
//+------------------------------------------------------------------+
int deinit()
  {
//----
   return(0);
  }
//+------------------------------------------------------------------+
//| expert start function                                            |
//+------------------------------------------------------------------+
int start()
  {
   if(Time[0] == prevtime) 
       return(0);
   prevtime = Time[0];
   int spread = 3;
   modifyOrder();
   
   /*
   if(perceptron() > 0 )
   closeExistingOrders(OP_SELL);
  if(perceptron() < 0 )
   closeExistingOrders(OP_BUY);
   */
//----
   if(IsTradeAllowed()) 
     {
       RefreshRates();
       spread = MarketInfo(Symbol(), MODE_SPREAD);
     } 
   else 
     {
       prevtime = Time[1];
       return(0);
     }
   int ticket = -1;
   double perc = perceptron();
// check for opened positiono
Comment("Trend is " + getTrend(perc) + " " + perc);
   int total = OrdersTotal();   
//----
   for(int i = 0; i < total; i++) 
     {
       OrderSelect(i, SELECT_BY_POS, MODE_TRADES); 
       // check for symbol & magic number
       if(OrderSymbol() == Symbol() && OrderMagicNumber() == MagicNumber) 
         {
           int prevticket = OrderTicket();
           // long position is opened
           if(OrderType() == OP_BUY) 
             {
               // check profit 
               if(Bid > (OrderStopLoss() + (sl * 2  + spread) * Point)) 
                 {               
                   if(perc < 0) 
                     { // reverse
                     
                       double tp = Bid - (Limit * Point);
                       ticket = OrderSend(Symbol(), OP_SELL, lots * 2, Bid, 3, 
                                          Ask + sl * Point, tp, "AI", MagicNumber, 0, Red); 
                       Sleep(30000);
                       //----
                       if(ticket < 0) 
                           prevtime = Time[1];
                       else 
                           //closeExistingOrders(OP_BUY);
                           OrderCloseBy(ticket, prevticket, Blue);   
                     } 
                   else 
                     { // trailing stop
                       if(!OrderModify(OrderTicket(), OrderOpenPrice(), Bid - sl * Point, 
                          0, 0, Blue)) 
                         {
                           Sleep(30000);
                           prevtime = Time[1];
                         }
                     }
                 }  
               // short position is opened
             } 
           else 
             {
               // check profit 
               if(Ask < (OrderStopLoss() - (sl * 2 + spread) * Point)) 
                 {
                   if(perc > 0) 
                     { // reverse
                       tp = Ask+ ( Limit *Point);
                       ticket = OrderSend(Symbol(), OP_BUY, lots * 2, Ask, 3, 
                                          Bid - sl * Point, tp, "AI", MagicNumber, 0, Blue); 
                       Sleep(30000);
                       //----
                       if(ticket < 0) 
                           prevtime = Time[1];
                      // else 
                      //    closeExistingOrders(OP_SELL); 
                     } 
                   else 
                     { // trailing stop
                       if(!OrderModify(OrderTicket(), OrderOpenPrice(), Ask + sl * Point, 
                          0, 0, Blue)) 
                         {
                           Sleep(30000);
                           prevtime = Time[1];
                         }
                     }
                 }  
             }
           // exit
           return(0);
         }
     }
// check for long or short position possibility
   if(perceptron() > 0) 
     { //long
       tp = Ask+ ( Limit *Point);
       ticket = OrderSend(Symbol(), OP_BUY, lots, Ask, 3, Bid - sl * Point, tp, "AI", 
                          MagicNumber, 0, Blue); 
       //----
       if(ticket < 0) 
         {
           Sleep(30000);
           prevtime = Time[1];
         }
     } 
   else 
     { // short
      tp = Bid - (Limit * Point);
       ticket = OrderSend(Symbol(), OP_SELL, lots, Bid, 3, Ask + sl * Point, tp, "AI", 
                          MagicNumber, 0, Red); 
       if(ticket < 0) 
         {
           Sleep(30000);
           prevtime = Time[1];
         }
     }
//--- exit
   return(0);
  }
//+------------------------------------------------------------------+
//| The PERCEPTRON - a perceiving and recognizing function           |
//+------------------------------------------------------------------+
double perceptron() 
  {
   double w1 = x1 - 100;
   double w2 = x2 - 100;
   double w3 = x3 - 100;
   double w4 = x4 - 100;
   double a1 = iAC(Symbol(), 0, 0);
   double a2 = iAC(Symbol(), 0, 7);
   double a3 = iAC(Symbol(), 0, 14);
   double a4 = iAC(Symbol(), 0, 21);
   return(w1 * a1 + w2 * a2 + w3 * a3 + w4 * a4);
  }
//+------------------------------------------------------------------+


string getTrend(double num){
   if(num > 0 ) return (" UP ");
   else return (" DOWN ");
}

void modifyOrder(){

   if(!TrailingStop)
      return;
      
   int total  = OrdersTotal();
   double dblTrailingStop = Point*TrailingStop;
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
      if(OrderMagicNumber()== MagicNumber && OrderSymbol()==Symbol()) {
      
       if(OrderType()==OP_SELL){
       
         if((OrderOpenPrice()-Ask)> dblTrailingStop){
         if((OrderStopLoss()>(Ask+ dblTrailingStop)) || (OrderStopLoss()==0))
           {
            OrderModify( OrderTicket(),OrderOpenPrice(),  Ask + dblTrailingStop,OrderTakeProfit(),0,Red);
            return(0);
           }
         }
        }
        
        if(OrderType()==OP_BUY
        ){
        
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


int macd()
  {
  
  int prd = PERIOD_H1;
  
   double dMacd1 = 8;
   double dMacd2 = 21;
   double dMacd3 =9;
  
   double one = iMACD(Symbol(),prd,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,1);
   double two = iMACD(Symbol(),prd,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,2);
   double three = iMACD(Symbol(),prd,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,3);
  
    
   if(one > two && two > three  ) return (1);   //long
   else if(one < two && two < three ) return (-1);  //short
   else return (0);   
}


void closeExistingOrders(int op){
   if(!CloseLosers)
      return;

   int total  = OrdersTotal();
   
   Print("Closing order " + op );
   
   double price = 0;
   if( op == OP_SELL ) price = Ask; else price = Bid;
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
             
        // if(  ( TimeCurrent()-OrderOpenTime())  > orderMaxDuration){
        //    OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
        // }   
     
         if(OrderType() == op  ){
            //Print("Closing order " + opName(op) );
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
            //continue;
         }
          
          
       }
   }
}