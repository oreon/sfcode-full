//+------------------------------------------------------------------+
//|                                                    NTCommons.mq4 |
//|                            Copyright © 2009, NeuralTraders Corp. |
//|                                    http://www.neuraltraders.info |
//+------------------------------------------------------------------+


#include <stdlib.mqh>
#include <WinUser32.mqh>

#import "kernel32.dll"
void GetLocalTime(int& TimeArray[]);
void GetSystemTime(int& TimeArray[]);
int GetTimeZoneInformation(int& TZInfoArray[]);
#import

int getTimeFrame(){
   return ( Period() );
}

int getOffset(){
       int offset =  TimeHour(TimeCurrent()) - getGmtHour() ;
       if(offset < -12 ) offset = offset + 24;
       return (offset);
}

double getLots(double risk, bool UseMM , double Lots){
   if(!UseMM) return (Lots);
   
   double lots = risk * (AccountEquity()/10000.0);
   double minlot = MarketInfo(Symbol(), MODE_MINLOT);
   if(lots <  minlot) lots = minlot;
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

datetime TimeGmt(){
   return ( TimeLocal() + (getGmtShift() * 60 * 60) );
} 

int getGmtHour(int offset = 0){
   if(IsTesting())
      return ( TimeHour(TimeCurrent()) -  offset);

   int local = TimeHour(TimeLocal());
   return ( MathMod( local + getGmtShift() , 24 )  + offset);  
}

int getOverBoughtOrSold(){
   double wpr = iWPR(NULL,0,14,0);
   if(wpr <= -90 )
      return (-1);
   if(wpr > -10)
      return (1);
     
   return (0);

}

bool nfpCheck(int MAGIC_NUM){
if(StringFind(Symbol(), "USD" ) == -1 ) return (0);
    int day = TimeDayOfWeek(TimeCurrent());
   if(day == 5 && TimeDay(TimeCurrent()) <= 7) {
     closeOrders(MAGIC_NUM);
         return (0);
    }

}

bool is5Digit(){
   return (Digits == 3 || Digits ==5);
}

double getSpread(){
   double div = 1;
   if(is5Digit()) div = 10.0; 
  return(  MarketInfo(Symbol(), MODE_SPREAD)/div );
}


string getSpreadStr(){
   return ( DoubleToStr(getSpread(), 2) );
}

#define TRADING 1
#define POSTEND 2
#define CLOSEHR 3
#define OUTSIDE 0
 

int tradeRange(int begin, int end, int closeAfter, int Offset){


  if (IsTesting()) int oset = Offset ; else oset = 0;

   int hour = getGmtHour(oset);
   int day = TimeDayOfWeek(getGmtHour());
   
   
   // Print("BEF " + hour + " " + begin + " "  + end ); 
   if(begin > end ) {
      end = end + 24;
      if (hour < begin ) hour = hour + 24;
   }
  
   //Print(hour + " " + begin + " "  + end ); 
   if(hour  >= begin && hour < end )
      return(TRADING);
   else if(hour >= end && MathMod(hour,24) < MathMod(end + closeAfter, 24))
      return (POSTEND);
   else if( ( MathMod(hour,24) > MathMod(end + closeAfter, 24))  || (day == 5 && hour >= 20 ) )
      return (CLOSEHR);


   return (OUTSIDE);
} 



int timeFilter(bool TimeFilter, int BeginHour, int EndHour, int CloseAfter, int MAGIC_NUM, bool TradeSundayAndFriday = false){
  int offset = TimeHour(TimeCurrent()) - getGmtHour() ;
  if(offset < 0 ) offset += 24;
  if(IsTesting())
   offset = 2;
  //Print (BeginHour + " " + offset);
      

  int day = TimeDayOfWeek(getGmtHour());
  //Print ("day is " + day);
 // nfpCheck(MAGIC_NUM);
  
  int currentHour = TimeHour(TimeCurrent());
      
  if(TimeFilter /*&& total == 0 */){
      int currentHr = getGmtHour();  //TimeHour(TimeCurrent()); 
      int origHr = currentHr; //getGmtHour();
      
      if(EndHour < BeginHour){
         EndHour += 24;
         if(currentHr <= EndHour )
            currentHr += 24;
      }
      
     // if(hr <= EndHour ) hr += 24;
      int orders = getOrderCount(MAGIC_NUM);
      

      if(orders > 0 && CloseAfter > 0 ){
      
        if( origHr == MathMod((EndHour + CloseAfter),24)  ){
        // Print ("hour is " + currentHr + " closing all ");
         closeOrders(MAGIC_NUM);
         return (0);
       }
     }
       
       //sunday & friday check
      if(!TradeSundayAndFriday){
         if(EndHour < BeginHour){
            if(day == 0 || ( day == 1  && origHr <= BeginHour) || ( day == 5  && origHr >= BeginHour) ) return (0); 
         }else 
            if(day == 0 || day == 5 ) return (0);
      
      }
     
       //if(!(day > 1 )  ) return (0); //sunday check
      // if(day == 5  && currentHr <= BeginHour ) return (0); 
 
      if(! ( currentHr >= BeginHour && currentHr < EndHour)){
       
      //  Comment (  );
 
          return(0);
      }else{
         // Comment( "Ready to trade \n");
      }
   }
   return (1);
}


int getOrderCount(int num){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
  
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)      
         continue;
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol() && (OrderType() == OP_BUY || OrderType() == OP_SELL) )
         ordersForThisEA++;
   }
  
   return ( ordersForThisEA );
}

int getOrderCountOp(int num, int op){
   int ordersForThisEA = 0;
   int total  = OrdersTotal();
  
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)      
         continue;
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol() && OrderType() == op )
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
            if(!OrderModify(OrderTicket(),OrderOpenPrice(), OrderStopLoss(), tp,0,Green))
              // Print("couldnt not modify to " +  tp  + ", current price:" +  Close[0] +  "  " + OrderOpenPrice() + " "  + ErrorDescription(GetLastError()) );
		       return(0);

         }
      }
}

int changeSL(int num, double sl){
    int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)       continue;
      
      if(OrderMagicNumber()== num && OrderSymbol() ==Symbol()  && OrderStopLoss() == 0){ 
      
         OrderModify(OrderTicket(),OrderOpenPrice(), sl ,OrderTakeProfit(),0,Green);
         
      }
   }

}


void changeTPop(int MAGIC_NUM, double tp, int op){

	   int total  = OrdersTotal();
	   for(int cnt=0;cnt<total;cnt++)
	    {
	      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)        continue;
	      if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol() && OrderType() == op) {
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
      
         if( OrderType() == OP_SELL ) price = Ask;
         else if ( OrderType() == OP_BUY ) price = Bid;
            OrderClose(OrderTicket(),OrderLots(),price,3,Violet); // close position
          
         switch(OrderType()){
            case OP_BUYLIMIT :
            case OP_BUYSTOP  :
            case OP_SELLLIMIT:
            case OP_SELLSTOP :
               OrderDelete(OrderTicket());
         }  
             
         Sleep(1000);
         
       }
   }
}

void deletePendingOrders(int MagicNumber){
  
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
             
         Sleep(1000);
         
       }
   }

}

int prevmod = 0;
void reduceProfit(int Magic,  int RiskReducer){
   int factor = RiskReducer;
   double newtp = 0;
   int total  = OrdersTotal();
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)    continue;
      
       int now = (TimeCurrent()-OrderOpenTime() )/ 300;

     
      if( OrderSymbol() == Symbol() && OrderMagicNumber()== Magic  && OrderProfit() < 0 && now != prevmod ) {
         
         if(OrderType() == OP_BUY)
            newtp = OrderTakeProfit() - factor * Point;
         if(OrderType() == OP_SELL)
            newtp = OrderTakeProfit() + factor * Point;
   
         OrderModify(OrderTicket(),OrderOpenPrice(),OrderStopLoss(),newtp,0,Green);
         prevmod = now;
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

double getAtr(string symbol, int period = PERIOD_H1, int duration = 20){
   return ( iATR(symbol,period,duration,0)/MarketInfo(symbol,MODE_POINT) );

}

int bollinger(int prd = 0){
   if (iBands(NULL,prd,20,2,0,PRICE_CLOSE,MODE_LOWER,0) > Low[0] ) return (1);
   if (iBands(NULL,prd,20,2,0,PRICE_CLOSE,MODE_UPPER,0) < High[0] ) return (-1);
   return (0);
}

int boll(int prd = 0){
   if (iBands(NULL,prd,20,2,0,PRICE_CLOSE,MODE_LOWER,0) > Close[0] ) return (1);
   if (iBands(NULL,prd,20,2,0,PRICE_CLOSE,MODE_UPPER,0) < Close[0] ) return (-1);
   return (0);
}


int sar(int prd= 0 ){
   if(iSAR(NULL,prd,0.02,0.2,0) < Close[0]) return(1);
    if(iSAR(NULL,prd,0.02,0.2,0) > Close[0]) return(-1);
   return (0);
   
}

int bollingerRebound(){
   if (Low[1] < iBands(NULL,0,20,2,0,PRICE_CLOSE,MODE_LOWER,1)  && Close[0] > iBands(NULL,0,20,2,0,PRICE_CLOSE,MODE_LOWER,0) ) return (1);
     if (High[1] > iBands(NULL,0,20,2,0,PRICE_CLOSE,MODE_HIGH,1)   && Close[0] < iBands(NULL,0,20,2,0,PRICE_CLOSE,MODE_HIGH,0) ) return (-1);
    return (0);
}





string opName(int op){
    if(op == OP_BUY )
      return (" BUY ");
   else 
      return (" SELL ");
}


int trend(string symbol, int tf)
  {
  
    int Pair_Trend = 0; // Flat
    
    double EMA_020 = iMA(symbol,tf,020,0,MODE_EMA,PRICE_CLOSE,0);
    double EMA_200 = iMA(symbol,tf,200,0,MODE_EMA,PRICE_CLOSE,0);
    
    if(EMA_020 < EMA_200)
      {
        Pair_Trend = -1; // Down
      }

    if(EMA_020 > EMA_200)
      {
        Pair_Trend = 1; // Up
      }
       
    return(Pair_Trend);
  
  }



void modifyF(int MAGIC_NUM){
   int total  = OrdersTotal();
   //double dblTrailingStop = Point*TrailingStop;
   
   double uval = 0;
   double lval = 0;
   double ufrac = 0;
   double lfrac = 0;
   
   for(int i = 5; i >= 0 ; i--){
   
      ufrac = iFractals(NULL, 0, MODE_UPPER, i);
      if(ufrac > 0 )
         uval = ufrac;
         
      lfrac = iFractals(NULL, 0, MODE_LOWER, i);
      
      if(lfrac > 0 )
         lval = lfrac;
         
       //Print (ufrac + " " + lfrac );
   }
   
   //Print ("finalvals " + uval + " " + lval );
   
   for(int cnt=0;cnt<total;cnt++)
    {
      if(OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES)==false)  continue;
      
      if(OrderMagicNumber()== MAGIC_NUM && OrderSymbol()==Symbol()) {
         
         if(OrderType()==OP_SELL && uval > 0){
               Print("setting sell orders sl to " +  OrderOpenPrice() + " --> " + uval);
           //if( OrderStopLoss()>uval && uval <  OrderOpenPrice()){
             if(Close[0] > uval)
                OrderClose(OrderTicket(),OrderLots(),Ask,3,Violet); 
              OrderModify( OrderTicket(),OrderOpenPrice(),  uval,OrderTakeProfit(),0,Red);
              //return(0);
             //}
   
          }
        
          if(OrderType()==OP_BUY && lval > 0 ){
               Print("setting buy orders sl to " +  OrderOpenPrice() + " --> " + lval);
               
               
                if(Ask < uval)
                OrderClose(OrderTicket(),OrderLots(),Bid,3,Violet); 
               
              //if( OrderStopLoss()< lval &&  lval >  OrderOpenPrice() ){
                 OrderModify(OrderTicket(),OrderOpenPrice(),lval,OrderTakeProfit(),0,Green);
             //    return(0);
             //}
         } 
         
       
      }
      
      
    }

}


void TrailingStop(int MagicNum, bool OnlyProfit = true, bool OnlyWithoutLoss = true)
{
   int tip,Ticket;
   bool error;
   double StLo,OSL,OOP;
  // n=0;
   for (int i=0; i<OrdersTotal(); i++) 
   {  if (OrderSelect(i, SELECT_BY_POS)==true)
      {  tip = OrderType();
         if (tip<2  && (OrderMagicNumber()==MagicNum ))
         {
         
            string smbl = OrderSymbol(); 
           // Print("checking for " +smbl);
          
           
             double DIGITS=MarketInfo(OrderSymbol(),MODE_DIGITS);
             
             double  ASK = MarketInfo(OrderSymbol(),MODE_ASK);
             double  BID = MarketInfo(OrderSymbol(),MODE_BID);
          
            OSL   = OrderStopLoss();
            OOP   = OrderOpenPrice();
            Ticket = OrderTicket();
            if (tip==OP_BUY)             
            {  //n++;f
               StLo = TrailOrderFrac(smbl,1,BID);        
               if (StLo <= OOP && OnlyProfit) continue;
               if (OSL  >= OOP && OnlyWithoutLoss) continue;
               if (StLo > OSL)
               {  error=OrderModify(Ticket,OOP,NormalizeDouble(StLo,DIGITS),
                  OrderTakeProfit(),0,White);
                 // Comment("TrailingStop ",Ticket," ",TimeToStr(TimeCurrent(),TIME_MINUTES));
                  Sleep(500);
                  if (!error) Print("Error order ",Ticket," TrailingStop ",
                              GetLastError(),"   ",smbl,"   SL ",StLo);
               }
            }                                         
            if (tip==OP_SELL)        
            {  //n++;
               StLo = TrailOrderFrac(smbl,-1,ASK);
               if (StLo==0) continue;        
               if (StLo >= OOP && OnlyProfit) continue;
               if (OSL  >= OOP && OnlyWithoutLoss) continue;
               if (StLo < OSL || OSL==0 )
               {  error=OrderModify(Ticket,OOP,NormalizeDouble(StLo,DIGITS),
                  OrderTakeProfit(),0,White);
                  Comment("TrailingStop "+Ticket," ",TimeToStr(TimeCurrent(),TIME_MINUTES));
                  Sleep(500);
                  if (!error) Print("Error order ",Ticket," TrailingStop ",
                              GetLastError(),"   ",smbl,"   SL ",StLo);
               }
            } 
         }
      }
   }
}


double  TrailOrderFrac (string smbl, int direction, double price, int period= PERIOD_M30)
{
        double fr;
        double delta = 0;
        int count = 50;
          double POINT=MarketInfo(smbl,MODE_POINT);
         if (direction== 1)
         for (int ii=1; ii< count; ii++) 
         {
            fr = iFractals(smbl,period,MODE_LOWER,ii);
            if (fr!=0) if (price-delta*POINT > fr) break;
            else fr=0;
         }
         if (direction==-1)
         for (int jj=1; jj<count; jj++) 
         {
            fr = iFractals(NULL,0,MODE_UPPER,jj);
            if (fr!=0) if (price+delta*POINT < fr) break;
            else fr=0;
         }
         //Print("Returnring fr " + fr);
         return(fr);
}    
