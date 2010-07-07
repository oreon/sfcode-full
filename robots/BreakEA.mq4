//+------------------------------------------------------------------+
//|                                                      BreakEA.mq4 |
//|                      Copyright © 2008, MetaQuotes Software Corp. |
//|                                        http://www.metaquotes.net |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2008, Neural Traders Corp."
#property link      "http://www.neuraltraders.info"


extern int       Stop = 250;
extern int       Limit = 60;
extern int       TrailingStop = 30;
extern double    Lots = 0.01;
extern int       MAX_ORDERS = 2;
extern bool CloseLosers = true;
int MAGIC_NUM = 766;
int MAGIC_NUM_BIG = 897;

// ===================
// User Inputs
// ===================
extern int       Boll_Period=20;
extern double    Boll_Dev=2.0;
extern int       Keltner_Period=20;
extern double    Keltner_Mul=1.5;
extern int       Momentum_Period=12;
extern int       Back_Bars=1000;
extern bool      Alert_On=true;
extern bool      On_Screen_Info=true;
extern double    StepTrailingStop = 10;
extern double    StepStopLoss = 80;
extern bool      UseIchi = true;

// =========================
// Buffer Array Declarations
// =========================
double Pos_Diff[10];   // Pos Histogram
double Neg_Diff[10];   // Neg Histogram
double Momentum[10];   // Momentum Indicator 
double   Squeeze[10];

datetime    Last_Alert_Time = 0;  // Used to prevent continuous alerts on current bar                         


//+------------------------------------------------------------------+
//| expert initialization function                                   |
//+------------------------------------------------------------------+
int init()
  {
//----
   
//----
   return(0);
  }
//+------------------------------------------------------------------+
//| expert deinitialization function                                 |
//+------------------------------------------------------------------+
int deinit()
  {
//----
   
//----
   return(0);
  }
  
 datetime prevtime; 
//+------------------------------------------------------------------+
//| expert start function                                            |
//+------------------------------------------------------------------+
int start()
  {
  
   if(Time[0] == prevtime) 
       return(0);
   prevtime = Time[0];
   
   modifyOrder();
  
   bool On_Screen_Info = true;
   if (On_Screen_Info == true)
      { 
      double ATR =iATR(Symbol(),PERIOD_D1,14,0);
      double Todays_Range =iHigh(Symbol(),PERIOD_D1,0) - iLow(Symbol(),PERIOD_D1,0);
      double ADX = iADX(Symbol(),0,12,PRICE_CLOSE,MODE_MAIN,0);
      double RSI = iRSI(Symbol(),0,12,PRICE_CLOSE,0);  
      double MACD_Main = iMACD(Symbol(),0,12,26,9,PRICE_CLOSE,MODE_MAIN,0);
      double MACD_Signal = iMACD(Symbol(),0,12,26,9,PRICE_CLOSE,MODE_SIGNAL,0);
      double Sto_Main = iStochastic(Symbol(),0,10,3,3,MODE_SMA,1,MODE_MAIN,0);    
      double Sto_Signal = iStochastic(Symbol(),0,10,3,3,MODE_SMA,1,MODE_SIGNAL,0);
     
      Momentum[0] = (Close[0] - Close[Momentum_Period]);
   
      Comment("-----------------------------------------------------------------",
              "\nDaily ATR(14): ",ATR," Todays Range: ",Todays_Range,
              "\nADX(12): ",NormalizeDouble(ADX,2)," RSI(12): ",NormalizeDouble(RSI,2),
              "\nMACD(12,26,9): ",MACD_Main,", ",MACD_Signal,
              "\nStochastic(10,3,3): ",NormalizeDouble(Sto_Main,2),", ",NormalizeDouble(Sto_Signal,2),
              "\n Mom:",Momentum[0], "\n ichi:", ichi(),
              "\n-----------------------------------------------------------------");
      }
      
       Momentum[0] = (Close[0] - Close[Momentum_Period]);
   
 
   int cnt = getTotoalOrderCount();
   
   //close all pending orders if any ;
   if(cnt == 0 ){
      //closeExistingOrders(OP_BUY);
      //closeExistingOrders(OP_SELL);
   }
 
   //======================
   // Main Indicator Loop
   //======================

   //int limit = 1;
  // int i = 0;
  
  int Counted_Bars = 100;
   if(Counted_Bars < 0) return;
   if(Counted_Bars > 0) Counted_Bars = Counted_Bars - Keltner_Period;
   int limit = 4; //Bars - Counted_Bars;
   
   
     for(int i = limit; i >= 0; i--) //main indicator FOR loop
     {
 
      double MA_Hi = iMA(Symbol(),0,Keltner_Period,0,MODE_SMA,PRICE_HIGH,i);
      double MA_Lo = iMA(Symbol(),0,Keltner_Period,0,MODE_SMA,PRICE_LOW,i);
      double Kelt_Mid_Band = iMA(Symbol(),0,Keltner_Period,0,MODE_SMA,PRICE_TYPICAL,i);
      double Kelt_Upper_Band = Kelt_Mid_Band + ((MA_Hi - MA_Lo)*Keltner_Mul);
      double Kelt_Lower_Band = Kelt_Mid_Band - ((MA_Hi - MA_Lo)*Keltner_Mul);
      double Boll_Upper_Band = iBands(Symbol(),0, Boll_Period,Boll_Dev,0,PRICE_CLOSE, MODE_UPPER,i);
      double Boll_Lower_Band = iBands(Symbol(),0, Boll_Period,Boll_Dev,0,PRICE_CLOSE, MODE_LOWER,i);
 
      
      // ======================
      // Buffer Calculations
      // ======================
      
      Momentum[i] = (Close[i] - Close[i+Momentum_Period]);
   
      
      if (Boll_Upper_Band >= Kelt_Upper_Band || Boll_Lower_Band <= Kelt_Lower_Band)
         {
         Pos_Diff[i] = (MathAbs(Boll_Upper_Band - Kelt_Upper_Band)+MathAbs(Boll_Lower_Band - Kelt_Lower_Band));
         Squeeze[i] = 1;
         }
      else  
         {
         Pos_Diff[i] = 0;
         }   
         
     
      if (Boll_Upper_Band < Kelt_Upper_Band && Boll_Lower_Band > Kelt_Lower_Band)
         {
         Neg_Diff[i] = -(MathAbs(Boll_Upper_Band - Kelt_Upper_Band)+MathAbs(Boll_Lower_Band - Kelt_Lower_Band));
         Squeeze[i] = 0;
         }
      else  
         {
         Neg_Diff[i] = 0;
         }         
         
         
      int signal = 0;
      
      if ( Squeeze[i] == 1 && Squeeze[i+1] == 0 && Momentum[i] > 0  && i == 0  ) signal = 1; 
      
       if(UseIchi){
         if (ichi() > 0 )  signal = 1 ; else signal = 0;
       }
       
       if ( Squeeze[i] == 1 && Squeeze[i+1] == 0 && Momentum[i] < 0 && i == 0 ) signal = -1;
        
       if(UseIchi){
         if (ichi() < 0 )  signal = -1 ; else signal = 0;
       }
       
 
      
      // ======================
      // Trigger Check
      // ======================
      if (Squeeze[i] == 1 && Squeeze[i+1] == 0 && Momentum[i] > 0  && i == 0  && ichi() > 0) // a cross above zero line and Mom > 0
         {         
         
         
         if (Last_Alert_Time != Time[0]) 
            {
            if (Alert_On == true) 
               Print("Alert: Possible Buy Breakout - "+Symbol()+" - "+TimeToStr(TimeLocal()));
            
            placeOrder(OP_BUY);
            Last_Alert_Time = Time[0];       
            ObjectCreate("Breakout"+Time[0],OBJ_ARROW,0,Time[0],Ask);
            ObjectSet("Breakout"+Time[0],OBJPROP_ARROWCODE,1);
            ObjectSet("Breakout"+Time[0],OBJPROP_COLOR,Blue);
            ObjectSet("Breakout"+Time[0],OBJPROP_WIDTH,2);        
            }
         }
         
         
      if (Squeeze[i] == 1 && Squeeze[i+1] == 0 && Momentum[i] < 0 && i == 0 && ichi() < 0 )   // a cross above zero line and Mom < 0
         {
         if (Last_Alert_Time != Time[0]) 
            {
            if (Alert_On == true) 
               Print("Alert: Possible Sell Breakout - "+Symbol()+" - "+TimeToStr(TimeLocal())); 
             placeOrder(OP_SELL);
           
            Last_Alert_Time = Time[0];          
            ObjectCreate("Breakout"+Time[0],OBJ_ARROW,0,Time[0],Bid);
            ObjectSet("Breakout"+Time[0],OBJPROP_ARROWCODE,1);
            ObjectSet("Breakout"+Time[0],OBJPROP_COLOR,Red);
            ObjectSet("Breakout"+Time[0],OBJPROP_WIDTH,2);             
            }
         }         

    }

   return(0);
  }
  
  
  string name = "BREAKEA";
  
  void placeOrder(int op){
   int ticket = 0;
   int ticket2 = 0;
   int totalOrders = getOrderCount(MAGIC_NUM, op);
   
    int factor = MathPow(10, Digits);
    double TodaysRange = factor * ( ( iHigh(Symbol(),PERIOD_D1,0) - iLow(Symbol(),PERIOD_D1,0))/4  );
  
    if( totalOrders >= MAX_ORDERS ){
      Print("Not placing " + opName(op) + " order as there are already orders" +totalOrders); 
      return(0);
   }
   
  // double profitTriggerForBig =  NormalizeDouble(((Limit*Point) * BigTrigger), Digits );
  // Print("profitTriggerForBig " + profitTriggerForBig );
   //double atr = iATR(NULL, PERIOD_H1, 14, 0 )* 10;
   //Print("atr " + atr);
    if(op == OP_BUY ){
      closeExistingOrders(OP_SELL);
      double sl = Bid - (Stop * Point);
      double tp = Ask+ ( Limit *Point);
      ticket=sendOrderRel(op,  sl, tp, Lots, name + getTimeFrame(), MAGIC_NUM);
   }
   else if( op == OP_SELL ){
      closeExistingOrders(OP_BUY);
      tp = Bid - (Limit * Point);
      sl = Ask+ (( Stop*Point) );
      ticket=sendOrderRel(op,  sl, tp, Lots, name + getTimeFrame(), MAGIC_NUM);
      
   }
   
   placeBigOrder(op);
}

void placeBigOrder(int op){

      int ticket = 0;
      double prevLots = lots();
      double newLots = 0;
      int step = 20;
      
      int step_orders = (Limit/step) ;
    
      if(op== OP_BUY ){
         
         for(int i = 1; i < step_orders; i++){
             //newLots = prevLots + newLots;
             double price = Ask + (i * step)* Point;
             double tp = price + (step * Point );
             double sl = Bid - (StepStopLoss * Point);
              ticket=sendPendingOrderRel(price, OP_BUYSTOP,  sl, tp, prevLots, "XX BIG" + getTimeFrame(), MAGIC_NUM_BIG);
             //prevLots = newLots;
         }
       }
      else{ 
     
         for(i = 1; i < step_orders; i++){
            //newLots = prevLots + newLots;Bid - (i*20)*Point
            price = Bid - (i  *step)*Point;
            tp = price - (step * Point);
            sl = Ask+ (( StepStopLoss * Point) );
            ticket=sendPendingOrderRel(price, OP_SELLSTOP,  sl, tp, prevLots, "XX BIG" + getTimeFrame(), MAGIC_NUM_BIG);
            //prevLots = newLots;
         }
 
      }
  

}


int sendPendingOrderRel(double price, int dir, double sl, double tp, double lots , string cmt, int magic ,  string symbol = "" ){
    int prmode = MODE_ASK;
    if(symbol == "" ) symbol = Symbol();
    if (dir == OP_SELL || dir == OP_SELLSTOP || dir == OP_SELLLIMIT)  prmode = MODE_BID;
    
    //double price  = MarketInfo(symbol, prmode);
    int ticket = OrderSend(symbol,dir,Lots,price,30,0,0,"YENBOT",magic,0,CLR_NONE);
    if(ticket >= 0 )
      OrderModify(ticket, OrderOpenPrice(),	sl, tp, 0, CLR_NONE);
    return (ticket);
}

int sendOrderRel(int dir, double sl, double tp, double lots , string cmt, int magic ,  string symbol = "" ){
    int prmode = MODE_ASK;
    if(symbol == "" ) symbol = Symbol();
    if (dir == OP_SELL )  prmode = MODE_BID;
    
    double price  = MarketInfo(symbol, prmode);
    int ticket = OrderSend(symbol,dir,Lots,price,30,0,0,"YENBOT",magic,0,CLR_NONE);
    if(ticket >= 0 )
      OrderModify(ticket, OrderOpenPrice(),	sl, tp, 0, CLR_NONE);
    return (ticket);
}


 string opName(int op){
    if(op == OP_BUY )
      return (" BUY ");
   else 
      return (" SELL ");
}


int getTotoalOrderCount(){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
             
      if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol() ==Symbol() ) {
         //if(OrderProfit() < 0 ) //already negative no need to open new trades
         // return (1000);
         ordersForThisEA++;
      }
   }
   
   return ( ordersForThisEA );
}


int getOrderCount(int num, int op){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
             
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol() && OrderType() == op) {
         //if(OrderProfit() < 0 ) //already negative no need to open new trades
         // return (1000);
         ordersForThisEA++;
      }
   }
   
   return ( ordersForThisEA );
}
  
  
int getTimeFrame(){
   return ( (Time[2] - Time[1])/60 );
} 

void modifyOrder(){

   if(!TrailingStop)
      return;
      
   int total  = OrdersTotal();
   double dblTrailingStop = Point*TrailingStop;
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
      if(OrderMagicNumber()== MAGIC_NUM  && OrderSymbol()==Symbol()) {

      
       if(OrderType()==OP_SELL){
       
         //close all pending orders if any ;
         if(OrderMagicNumber()== MAGIC_NUM && OrderStopLoss()- Bid <  8 ){
            //closeOrders(OP_BUY);
            //closeExistingOrders(OP_SELL);
         }
       
         if((OrderOpenPrice()-Ask)> dblTrailingStop){
         if((OrderStopLoss()>(Ask+ dblTrailingStop)) || (OrderStopLoss()==0))
           {
            OrderModify( OrderTicket(),OrderOpenPrice(),  Ask + dblTrailingStop,OrderTakeProfit(),0,Red);
            //return(0);
           }
         }
        }
        
        if(OrderType()==OP_BUY){
        
            //close all pending orders if any ;
         if(OrderMagicNumber()== MAGIC_NUM && MathAbs (Ask - OrderStopLoss()) <  8 ){
            //closeExistingOrders(OP_BUY);
         }
        
        
           if(Bid-OrderOpenPrice()>dblTrailingStop)
           {
           
            if(OrderStopLoss()<Bid-dblTrailingStop)
              {
               OrderModify(OrderTicket(),OrderOpenPrice(),Bid- dblTrailingStop,OrderTakeProfit(),0,Green);
               //return(0);
              }
           }
        }
       } 
       
     }
     
     
     dblTrailingStop = Point *  StepTrailingStop;
     for(cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
      if(OrderMagicNumber()== MAGIC_NUM_BIG  && OrderSymbol()==Symbol()) {
      
       if(OrderType()==OP_SELL){
       
         if((OrderOpenPrice()-Ask)> dblTrailingStop){
         if((OrderStopLoss()>(Ask+ dblTrailingStop)) || (OrderStopLoss()==0))
           {
            OrderModify( OrderTicket(),OrderOpenPrice(),  Ask + dblTrailingStop,OrderTakeProfit(),0,Red);
            //return(0);
           }
         }
        }
        
        if(OrderType()==OP_BUY){
        
           if(Bid-OrderOpenPrice()>dblTrailingStop)
           {
           
            if(OrderStopLoss()<Bid-dblTrailingStop)
              {
               OrderModify(OrderTicket(),OrderOpenPrice(),Bid- dblTrailingStop,OrderTakeProfit(),0,Green);
               //return(0);
              }
           }
        }
       } 
       
     }

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
     
      if( OrderSymbol() == Symbol() && OrderMagicNumber()== MAGIC_NUM  ) {
          
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
            Print("Closing order " + opName(op) );
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
            //continue;
         }
          
          
       }
   }
}

double lots(){
   double lot = Lots * (AccountEquity()/5000);
   
   if(lot < 0.01) return (0.01);
   
   return  (lot);

}


int ichi(){
  double ichi =  iIchimoku(NULL, PERIOD_H4, 9, 26, 52, MODE_SENKOUSPANA, 1);
  if(ichi > Close[1]) return (-1);
  if(ichi < Close[1]) return (1);
  return (0);    

}
//+------------------------------------------------------------------+