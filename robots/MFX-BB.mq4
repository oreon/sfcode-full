//+------------------------------------------------------------------+
//|                                          The Holy Grail v1.6.mq4 |
//|              Copyright © 2009, NT. All rights reserved |
//|                                        http://www.metaquotes.net |
//+------------------------------------------------------------------+

#property show_inputs
#include <NTCommons.mqh>

//+------------------------------------------------------------------+
//| expert External variables                                        |
//+------------------------------------------------------------------+
extern int    GMTShift = -5;

extern int    Magic = 66699;
extern string CommentText  = "MFX_BB";

//+------------------------------------------------------------------+
//| expert Internal variables                                        |
//+------------------------------------------------------------------+

string Pairs[] = {"AUDJPY","AUDUSD","CADJPY","CHFJPY","EURAUD", "GBPCHF", 
                  "EURCAD","EURCHF","EURGBP","EURJPY","EURUSD",
                  "GBPJPY","GBPUSD","USDCAD","USDCHF","USDJPY"};

string Author  = "© 2009 NeuralTraders. All rights reserved.";

int M05 = PERIOD_M5;
int M15 = PERIOD_M15;
int M30 = PERIOD_M30;
int H01 = PERIOD_H1;
int H04 = PERIOD_H4;
int D01 = PERIOD_D1;

int OB = 80;
int ML = 50;
int OS = 20;

int Decimals;
extern int Trail = 20;

//+------------------------------------------------------------------+
//| expert Initialization function                                   |
//+------------------------------------------------------------------+

int init()
  {

    string Suffix = StringSubstr(Symbol(),6,StringLen(Symbol())-6);

    for(int a = 0; a < ArraySize(Pairs); a++)
      {
        Pairs[a] = Pairs[a] + Suffix;
      }
      
    if(Digits == 3 || Digits == 5 )
      Trail = Trail * 10;
    
    double Step = MarketInfo(Symbol(),MODE_LOTSTEP);

    if(Step == 0.01) Decimals = 2;
    if(Step == 0.10) Decimals = 1;
    if(Step == 1.00) Decimals = 0;

    if(Magic == 0) Magic = AccountNumber();
    
    Comment("\nWaiting for tick update...");

    return(0);
    
  }
  
//+------------------------------------------------------------------+
//| expert Deinitialization function                                 |
//+------------------------------------------------------------------+

int deinit()
  {

    Comment("");
    
    return(0);

  }
  
//+------------------------------------------------------------------+
//| expert Start function                                            |
//+------------------------------------------------------------------+

int start()
  {
    
    double Lots = NormalizeDouble(AccountBalance()/120000,Decimals);
    double Min  = MarketInfo(Symbol(),MODE_MINLOT);
    double Max  = MarketInfo(Symbol(),MODE_MAXLOT);

    if(Lots < Min) Lots = Min;
    if(Lots > Max) Lots = Max;

    string Status = "Trading enabled...";
    
    int gmtHH = TimeHour(TimeGmt());
    
    //if(GMTShift < 0) gmtHH = gmtHH - MathAbs(GMTShift);
    //if(GMTShift > 0) gmtHH = gmtHH + MathAbs(GMTShift);
    
    TrailingStop();
    
    bool news  = NewsExist();
      if(news) 
        modifyOrder(Magic, Trail);
    
    if((TimeDayOfWeek(TimeGmt()) == 5 && TimeHour(TimeGmt()) > 12) ||
       (TimeDayOfWeek(TimeGmt()) == 0 && TimeHour(TimeGmt()) < 23) ||
        TimeDayOfWeek(TimeGmt()) == 6 ||news)
      {
        Status = "Trading disabled...";
      }
    
    Comment("\n",Author,"\n\n",Status,"\n\n","Lots = ",DoubleToStr(Lots,2));

    if(StringFind(Status,"disabled",0) > 0)
      {
        return(0);
      }

    for(int b = 0; b < ArraySize(Pairs); b++)
      {
      
        if(OrdersTotal() == 6)
          {
            break;
          }

        if(MarketInfo(Pairs[b],MODE_BID) > 0 && MarketInfo(Pairs[b],MODE_ASK) > 0)
          {      
            
            int Rsi = 2;

            if((StringSubstr(Pairs[b],0,3) == "AUD" && (gmtHH >= 22 || gmtHH < 06)) || 
               (StringSubstr(Pairs[b],3,3) == "AUD" && (gmtHH >= 22 || gmtHH < 06)) ||
               (StringSubstr(Pairs[b],0,3) == "CAD" && (gmtHH >= 13 && gmtHH < 21)) || 
               (StringSubstr(Pairs[b],3,3) == "CAD" && (gmtHH >= 13 && gmtHH < 21)) ||
               (StringSubstr(Pairs[b],0,3) == "CHF" && (gmtHH >= 07 && gmtHH < 15)) || 
               (StringSubstr(Pairs[b],3,3) == "CHF" && (gmtHH >= 07 && gmtHH < 15)) ||
               (StringSubstr(Pairs[b],0,3) == "EUR" && (gmtHH >= 07 && gmtHH < 15)) || 
               (StringSubstr(Pairs[b],3,3) == "EUR" && (gmtHH >= 07 && gmtHH < 15)) ||
               (StringSubstr(Pairs[b],0,3) == "GBP" && (gmtHH >= 08 && gmtHH < 16)) || 
               (StringSubstr(Pairs[b],3,3) == "GBP" && (gmtHH >= 08 && gmtHH < 16)) ||
               (StringSubstr(Pairs[b],0,3) == "JPY" && (gmtHH >= 24 || gmtHH < 08)) || 
               (StringSubstr(Pairs[b],3,3) == "JPY" && (gmtHH >= 24 || gmtHH < 08)) ||
               (StringSubstr(Pairs[b],0,3) == "NZD" && (gmtHH >= 22 || gmtHH < 06)) || 
               (StringSubstr(Pairs[b],3,3) == "NZD" && (gmtHH >= 22 || gmtHH < 06)) ||
               (StringSubstr(Pairs[b],0,3) == "USD" && (gmtHH >= 13 && gmtHH < 21)) ||
               (StringSubstr(Pairs[b],3,3) == "USD" && (gmtHH >= 13 && gmtHH < 21)))  
              {
                Rsi = 3;
              }            

            double RSI_M05 = iRSI(Pairs[b],M05,Rsi,PRICE_CLOSE,0);
            double RSI_M15 = iRSI(Pairs[b],M15,Rsi,PRICE_CLOSE,0);
            double RSI_H01 = iRSI(Pairs[b],H01,Rsi,PRICE_CLOSE,0);
            double RSI_H04 = iRSI(Pairs[b],H04,Rsi,PRICE_CLOSE,0);
            
            int bb = bollingerSym(Pairs[b], PERIOD_M30);

            if(Trend(Pairs[b],H01) == "U" /*&& Trend(Pairs[b],H01) == "U" &&
               Trend(Pairs[b],H04) == "U"*/ && Trend(Pairs[b],D01) == "U")
              {
                if (bb > 0)
                  {
                    if(TradeExist(Pairs[b]) == false && lastTradeHitSL(Pairs[b]) == false)
                      {
                        if(IsTradeAllowed() == true) SendOrder(Pairs[b],OP_BUY,Lots);
                      }
                  }
              }
               
            if(Trend(Pairs[b],H01) == "D" /*&& Trend(Pairs[b],H01) == "D" && 
               Trend(Pairs[b],H04) == "D" */ && Trend(Pairs[b],D01) == "D")
              {
                if(bb < 0)
                {
                    if(TradeExist(Pairs[b]) == false && lastTradeHitSL(Pairs[b]) == false)
                      {
                        if(IsTradeAllowed() == true) SendOrder(Pairs[b],OP_SELL,Lots);
                      }
                  }
              }
              
          }
      
      }
      
    return(0);

  }

//+------------------------------------------------------------------+
//| expert TradeExists function                                      |
//+------------------------------------------------------------------+

bool TradeExist(string symbol)
  {

    bool Found = false;

    for(int c = 0; c <= OrdersTotal(); c++)
      {
        if(OrderSelect(c,SELECT_BY_POS,MODE_TRADES) == true)
          {
            if(OrderSymbol() == symbol && OrderMagicNumber() == Magic)
              {
                Found = true;
                break;
              }
          }
      }
    
    return(Found);

  }
  
  
  bool lastTradeHitSL(string symbol)
  {

    bool Found = false;

    for(int c = 0; c <= OrdersHistoryTotal(); c++)
      {
        if(OrderSelect(c,SELECT_BY_POS,MODE_TRADES) == true)
          {
            if(OrderSymbol() == symbol && OrderMagicNumber() == Magic && OrderProfit() < 0 )
              {
               if( ((TimeCurrent() - OrderCloseTime()) ) <  30 * 60 ) 
               Print("Trade hit sl for " + symbol + " not taking trade ");
                Found = true;
                break;
              }
          }
      }
    
    return(Found);

  }

//+------------------------------------------------------------------+
//| expert SendOrder function                                        |
//+------------------------------------------------------------------+  

void SendOrder(string symbol, int dir, double lots)
  {
    
    double price;
    double point;
    double sl;
    double tp;

    int ticket = -1;
    int spread = 10;
    int digit  = 0;
    
    int take = 1.8 * iATR(symbol,H01,20,0)/MarketInfo(symbol,MODE_POINT);
    int stop = 3.0 * iATR(symbol,H01,20,0)/MarketInfo(symbol,MODE_POINT);
        
    if(MarketInfo(symbol,MODE_DIGITS) == 3 || MarketInfo(symbol,MODE_DIGITS) == 5)
      {
        spread *= 10;
      }
    
    take += MarketInfo(symbol,MODE_SPREAD);
    
    if(dir == OP_BUY && MarketInfo(symbol,MODE_SPREAD) <= spread)
      {
        while(ticket < 0)
          {
            RefreshRates();
            price  = MarketInfo(symbol,MODE_ASK);
            point  = MarketInfo(symbol,MODE_POINT);
            digit  = MarketInfo(symbol,MODE_DIGITS);
            sl     = NormalizeDouble(price-stop*point,digit);
            tp     = NormalizeDouble(price+take*point,digit);
                        ticket = OrderSend(symbol,dir,lots,price,30,0,0,CommentText,Magic,0,CLR_NONE);
            if(ticket >= 0 )
             OrderModify(ticket, OrderOpenPrice(),	sl, tp, 0, CLR_NONE);

            Print(GetLastError());
            Sleep(1000);
          }
      }
      
    if(dir == OP_SELL && MarketInfo(symbol,MODE_SPREAD) <= spread)
      {
        while(ticket < 0)
          {
            RefreshRates();
            price  = MarketInfo(symbol,MODE_BID);
            point  = MarketInfo(symbol,MODE_POINT);
            digit  = MarketInfo(symbol,MODE_DIGITS);
            sl     = NormalizeDouble(price+stop*point,digit);
            tp     = NormalizeDouble(price-take*point,digit);
             ticket = OrderSend(symbol,dir,lots,price,30,0,0,CommentText,Magic,0,CLR_NONE);
            if(ticket >= 0 )
             OrderModify(ticket, OrderOpenPrice(),	sl, tp, 0, CLR_NONE);

            Print(GetLastError());
            Sleep(1000);
          }
      }
  
  }



//+------------------------------------------------------------------+
//| expert Trend function                                            |
//+------------------------------------------------------------------+  

string Trend(string symbol, int tf)
  {
  
    string Pair_Trend = "F"; // Flat
    
    double EMA_020 = iMA(symbol,tf,020,0,MODE_EMA,PRICE_CLOSE,0);
    double EMA_200 = iMA(symbol,tf,200,0,MODE_EMA,PRICE_CLOSE,0);
    
    if(EMA_020 < EMA_200)
      {
        Pair_Trend = "D"; // Down
      }

    if(EMA_020 > EMA_200)
      {
        Pair_Trend = "U"; // Up
      }
       
    return(Pair_Trend);
  
  }
bool OnlyProfit = true;
bool OnlyWithoutLoss = true;


void TrailingStop()
{
   int tip,Ticket;
   bool error;
   double StLo,OSL,OOP;
  // n=0;
   for (int i=0; i<OrdersTotal(); i++) 
   {  if (OrderSelect(i, SELECT_BY_POS)==true)
      {  tip = OrderType();
         if (tip<2  && (OrderMagicNumber()==Magic ))
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
            {  //n++;
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
       
       
       
double  TrailOrderFrac (string smbl, int tip, double price, int period= PERIOD_M30)
{
        double fr;
        double delta = 0;
          double POINT=MarketInfo(smbl,MODE_POINT);
         if (tip== 1)
         for (int ii=1; ii<100; ii++) 
         {
            fr = iFractals(smbl,period,MODE_LOWER,ii);
            if (fr!=0) if (price-delta*POINT > fr) break;
            else fr=0;
         }
         if (tip==-1)
         for (int jj=1; jj<100; jj++) 
         {
            fr = iFractals(NULL,0,MODE_UPPER,jj);
            if (fr!=0) if (price+delta*POINT < fr) break;
            else fr=0;
         }
         //Print("Returnring fr " + fr);
         return(fr);
}    
