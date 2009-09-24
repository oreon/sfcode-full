//+------------------------------------------------------------------+
//|                                                   XCalper-V1.mq4 |
//|                      Copyright © 2009, MetaQuotes Software Corp. |
//|                                        http://www.metaquotes.net |
//+------------------------------------------------------------------+
#property copyright "Copyright © 2009, MetaQuotes Software Corp."
#property link      "http://www.metaquotes.net"


#property copyright "NeuralTraders Corp"
//----

#include "include\NTCommons.mqh"

/*extern*/ int     MMType=1;       // Тип ММ: 0-Lots, 1-как было в 1.2, 2-мартингейл (коэффициент LotExponent)
/*extern*/ bool    UseClose=false; // закрытие по убытку PipStep. рекомендутся false
/*extern*/ bool    UseAdd=true;    // переоткрытие с новым лотом. лот для переоткрытия считается по LotExponent независимо от MMType рекомендутся = true
extern double  LotExponent=1; // умножение лотов в серии по експоненте для вывода в безубыток. первый лот 0.1, серия: 0.16, 0.26, 0.43 ...
extern double  slip=3;   // допустимое проскальзывание цены в пипсах
extern double  Lots=0.04; // теперь можно и микролоты 0.01 при этом если стоит 0.1 то следующий лот в серии будет 0.16
extern double  TakeProfit=35; // Уровень прибыли в пипсаз от цены открытия.
double  Stoploss=500; // эти три параметра не работают
double  TrailStart=10;
double  TrailStop=10;
extern double  PipStep=200; // растоянию в пипсах убытка на котором открываеться следующий ордер колена.
extern int     MaxTrades=10;
extern bool    UseEquityStop=true;
extern double  TotalEquityRisk=40; //loss as a percentage of equity
extern bool    UseTrailingStop=false;
extern bool    UseTimeOut=false;
extern double  MaxTradeOpenHours=48;
extern bool  UseMoneyManagement = true;
//----
int MagicNumber=12324779;
double PriceTarget, StartEquity, BuyTarget, SellTarget;
double AveragePrice, SellLimit, BuyLimit;
double LastBuyPrice, LastSellPrice, ClosePrice, Spread;
int flag;
string EAName="XCalperV3";
datetime timeprev=0, expiration;
int NumOfTrades=0;
double iLots;
int cnt=0, total;
double Stopper=0;
bool TradeNow=false, LongTrade=false, ShortTrade=false;
int ticket;
bool NewOrdersPlaced=false;
extern int TrailBreakOut = 120;

extern bool TimeFilter = true;
extern int GmtBeginHour = 20;
extern int GmtEndHour = 3;
extern bool CloseAtPeriodEnd = false;
extern bool TradeMultipleInSameTimeFrame = true;
extern int BeginHour = 22;
extern double RiskFactor = 0.2;


double dMacd1 = 12;
double dMacd2 = 26;
double dMacd3 =9;
extern int Key = 20000;



//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
int init()
  {
   Spread=MarketInfo(Symbol(), MODE_SPREAD)*Point;
   return(0);
  }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
int deinit()
  {
   return(0);
  }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
  int start()
  {
  
   if(!IsDemo()){
    if(  !validateKey(Key) ) {
      Comment( Key + " is not valid - please enter a valid license key .");
   }
  }
  
   total=CountTrades();
    
   if(total == 1 ) 
    modifyOrder(MagicNumber, TrailBreakOut);
    
    
  if(TimeFilter /*&& total == 0 */){
      int hr = TimeHour(TimeCurrent());
      if(hr <= 5 ) hr += 24;
 
      if(hr == BeginHour - 2 && CloseAtPeriodEnd){
         Print ("hour is " + 7 + " closing all ");
         CloseThisSymbolAll();
       }
 
      if(! ( hr >= BeginHour && hr < 29)){
          Comment( "Now is " +  hr + ":00  Trading will begin at " + BeginHour + ":00 \n");
          return(0);
      }else{
          Comment( "Ready to trade \n");
      }
      
   
      
   }
 
     if (UseTrailingStop)
     {
      TrailingAlls(TrailStart, TrailStop, AveragePrice);
     }
    
     if (UseTimeOut){
        if(CurTime()>=expiration)
        {
         CloseThisSymbolAll();
         Print("Closed All due to TimeOut");
        }
     }
    
     if(timeprev==Time[0])
     {
         if(!TradeMultipleInSameTimeFrame) return(0);
     }
      timeprev=Time[0];
//----
   double CurrentPairProfit=CalculateProfit();
     if(UseEquityStop){
        if(CurrentPairProfit<0 && MathAbs(CurrentPairProfit)>(TotalEquityRisk/100)*AccountEquityHigh())
        {
         CloseThisSymbolAll();
         Print("Closed All due to Stop Out");
         NewOrdersPlaced=false;
        }
     }
  
//----
     if (total==0)
     {
      flag=0;
     }
   double LastBuyLots;
   double LastSellLots;
     for(cnt=OrdersTotal()-1;cnt>=0;cnt--){// поиск последнего направления
      OrderSelect(cnt,SELECT_BY_POS,MODE_TRADES);
      if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)continue;
      if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
           if(OrderType()==OP_BUY)
           {
            LongTrade=true;
            ShortTrade=false;
            LastBuyLots=OrderLots();
            break;
           }
      if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
           if(OrderType()==OP_SELL)
           {
            LongTrade=false;
            ShortTrade=true;
            LastSellLots=OrderLots();
            break;
           }
     }
     if(total>0 && total<=MaxTrades)
     {
      RefreshRates();
      LastBuyPrice=FindLastBuyPrice();
      LastSellPrice=FindLastSellPrice();
        if(LongTrade && (LastBuyPrice - Ask)>=(PipStep*Point))
        {
         TradeNow=true;
        }
        if(ShortTrade && (Bid - LastSellPrice)>=(PipStep*Point))
        {
         TradeNow=true;
        }
     }
     if (total < 1)
     {
      ShortTrade=false;
      LongTrade=false;
      TradeNow=true;
      StartEquity=AccountEquity();
     }
     if (TradeNow)
     {
      LastBuyPrice=FindLastBuyPrice();
      LastSellPrice=FindLastSellPrice();
        if(ShortTrade)
        {
           if(UseClose)
           {
            fOrderCloseMarket(false,true);
            iLots=LotExponent*LastSellLots;
           }
           else
           {
            iLots=fGetLots(OP_SELL);
           }
           if(UseAdd)
           {
            NumOfTrades=total;
              if(iLots>0)
              {//#
               RefreshRates();
               ticket=OpenPendingOrder(OP_SELL,iLots,Bid,slip,Ask,0,0,EAName+"-"+NumOfTrades,MagicNumber,0,HotPink);
               if(ticket<0){Print("Error: ",GetLastError()); return(0);}
               LastSellPrice=FindLastSellPrice();
               TradeNow=false;
               NewOrdersPlaced=true;
              }//#
           }
        }
        else if (LongTrade)
        {
              if(UseClose)
              {
               fOrderCloseMarket(true,false);
               iLots=LotExponent*LastBuyLots;
              }
              else
              {
               iLots=fGetLots(OP_BUY);
              }
              if(UseAdd)
              {
               NumOfTrades=total;
                 if(iLots>0)
                 {//#
                  ticket=OpenPendingOrder(OP_BUY,iLots,Ask,slip,Bid,0,0,EAName+"-"+NumOfTrades,MagicNumber,0,Lime);
                  if(ticket<0)
                  {Print("Error: ",GetLastError()); return(0);}
                  LastBuyPrice=FindLastBuyPrice();
                  TradeNow=false;
                  NewOrdersPlaced=true;
                 }//#
              }
           }
     }
     if (TradeNow && total < 1)
     {
     
   
      double PrevCl=iClose(Symbol(),0,2);
      double CurrCl=iClose(Symbol(),0,1);
     
     
      SellLimit=Bid;
      BuyLimit=Ask;
        if(!ShortTrade && !LongTrade)
        {
         NumOfTrades=total;
         int signal = getSignal();
           //if(PrevCl > CurrCl)
           if(signal < 0 )
           {
            iLots=fGetLots(OP_SELL);
              if(iLots>0)
              {//#
              if(UseMoneyManagement && total < 1)
               iLots = getLots(RiskFactor);
               
               ticket=OpenPendingOrder(OP_SELL,iLots,SellLimit,slip,SellLimit,0,0,EAName+"-"+NumOfTrades,MagicNumber,0,HotPink);
               if(ticket<0){Print(iLots,"Error: ",GetLastError()); return(0);
               }
               LastBuyPrice=FindLastBuyPrice();
               NewOrdersPlaced=true;
              }//#
           }
           else if( signal > 0 )
           {
            iLots=fGetLots(OP_BUY);
              if(iLots>0)
              {//#     
              
                if(UseMoneyManagement && total < 1)
               iLots = getLots(RiskFactor);
              
               ticket=OpenPendingOrder(OP_BUY,iLots,BuyLimit,slip,BuyLimit,0,0,EAName+"-"+NumOfTrades,MagicNumber,0,Lime);
               if(ticket<0){Print(iLots,"Error: ",GetLastError()); return(0);}
               LastSellPrice=FindLastSellPrice();
               NewOrdersPlaced=true;
              }//#
           }
        }
      if(ticket>0) expiration=CurTime()+MaxTradeOpenHours*60*60;
      TradeNow=false;
     }
//----------------------- CALCULATE AVERAGE OPENING PRICE
   total=CountTrades();
   AveragePrice=0;
   double Count=0;
   for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
     {
      OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
      if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
         continue;
      if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
         if(OrderType()==OP_BUY || OrderType()==OP_SELL)
           {
            AveragePrice=AveragePrice+OrderOpenPrice()*OrderLots();
            Count=Count + OrderLots();
           }
     }
   if(total > 0)
      AveragePrice=NormalizeDouble(AveragePrice/Count, Digits);
//----------------------- RECALCULATE STOPLOSS & PROFIT TARGET BASED ON AVERAGE OPENING PRICE
   if(NewOrdersPlaced)
      for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
        {
         OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
         if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
            continue;
         if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
            if(OrderType()==OP_BUY) // Calculate profit/stop target for long
              {
               PriceTarget=AveragePrice+(TakeProfit*Point);
               BuyTarget=PriceTarget;
               Stopper=AveragePrice-(Stoploss*Point);
               //      Stopper=0;
               flag=1;
              }
         if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
            if(OrderType()==OP_SELL) // Calculate profit/stop target for short
              {
               PriceTarget=AveragePrice-(TakeProfit*Point);
               SellTarget=PriceTarget;
               Stopper=AveragePrice+(Stoploss*Point);
               //      Stopper=0;
               flag=1;
              }
        }
//----------------------- IF NEEDED CHANGE ALL OPEN ORDERS TO NEWLY CALCULATED PROFIT TARGET   
   if(NewOrdersPlaced)
      if(flag==1)// check if average has really changed
        {
         for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
           {
            //     PriceTarget=total;
            OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
            if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
               continue;
            if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
               //      OrderModify(OrderTicket(),0,Stopper,PriceTarget,0,Yellow);// set all positions to averaged levels
               OrderModify(OrderTicket(),AveragePrice,OrderStopLoss(),PriceTarget,0,Yellow);// set all positions to averaged levels
            NewOrdersPlaced=false;
           }
        }
  }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
double ND(double v){return(NormalizeDouble(v,Digits));}
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
  int fOrderCloseMarket(bool aCloseBuy=true,bool aCloseSell=true)
  {
   int tErr=0;
     for(int i=OrdersTotal()-1;i>=0;i--)
     {
        if(OrderSelect(i,SELECT_BY_POS,MODE_TRADES))
        {
           if(OrderSymbol()==Symbol() && OrderMagicNumber()==MagicNumber)
           {
              if(OrderType()==OP_BUY && aCloseBuy)
              {
               RefreshRates();
                 if(!IsTradeContextBusy())
                 {
                    if(!OrderClose(OrderTicket(),OrderLots(),ND(Bid),5,CLR_NONE))
                    {
                     Print("Error close BUY "+OrderTicket());//+" "+fMyErDesc(GetLastError()));
                     tErr=-1;
                    }
                 }
                 else
                 {
                  static int lt1=0;
                    if(lt1!=iTime(NULL,0,0))
                    {
                     lt1=iTime(NULL,0,0);
                     Print("Need close BUY "+OrderTicket()+". Trade Context Busy");
                    }
                  return(-2);
                 }
              }
              if(OrderType()==OP_SELL && aCloseSell)
              {
               RefreshRates();
                 if(!IsTradeContextBusy())
                 {
                    if(!OrderClose(OrderTicket(),OrderLots(),ND(Ask),5,CLR_NONE))
                    {
                     Print("Error close SELL "+OrderTicket());//+" "+fMyErDesc(GetLastError()));
                     tErr=-1;
                    }
                 }
                 else
                 {
                  static int lt2=0;
                    if(lt2!=iTime(NULL,0,0))
                    {
                     lt2=iTime(NULL,0,0);
                     Print("Need close SELL "+OrderTicket()+". Trade Context Busy");
                    }
                  return(-2);
                 }
              }
           }
        }
     }
   return(tErr);
  }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
  double fGetLots(int aTradeType)
  {
   double tLots;
     switch(MMType)
     {
         case 0:
            tLots=Lots;
            break;
         case 1:
            Lots = getLots(RiskFactor);
            tLots=Lots*MathPow(LotExponent,NumOfTrades);
            break;
         case 2:
            int LastClosedTime=0;
            tLots=Lots;
              for(int i=OrdersHistoryTotal()-1;i>=0;i--)
              {
                 if(OrderSelect(i,SELECT_BY_POS,MODE_HISTORY))
                 {
                    if(OrderSymbol()==Symbol() && OrderMagicNumber()==MagicNumber)
                    {
                       if(LastClosedTime<OrderCloseTime())
                       {
                        LastClosedTime=OrderCloseTime();
                          if(OrderProfit()<0)
                          {
                           tLots=OrderLots()*LotExponent;
                          }
                          else
                          {
                           tLots=Lots;
                          }
                       }
                    }
                 }
                 else
                 {
                  return(-3);
                 }
              }
            break;
        }
        if(AccountFreeMarginCheck(Symbol(),aTradeType,tLots)<=0)
        {
         return(-1);
        }
        if(GetLastError()==134)
        {
         return(-2);
        }
      return(tLots);
     }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         int CountTrades()
           {
            int count=0;
            int trade;
            for(trade=OrdersTotal()-1;trade>=0;trade--)
              {
               OrderSelect(trade,SELECT_BY_POS,MODE_TRADES);
               if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
                  continue;
               if(OrderSymbol()==Symbol()&&OrderMagicNumber()==MagicNumber)
                  if(OrderType()==OP_SELL || OrderType()==OP_BUY)
                     count++;
              }//for
            return(count);
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         void CloseThisSymbolAll()
           {
            int trade;
            for(trade=OrdersTotal()-1;trade>=0;trade--)
              {
               OrderSelect(trade,SELECT_BY_POS,MODE_TRADES);
               if(OrderSymbol()!=Symbol())
                  continue;
               if(OrderSymbol()==Symbol() && OrderMagicNumber()== MagicNumber)
                 {
                  if(OrderType()==OP_BUY)
                     OrderClose(OrderTicket(),OrderLots(),Bid,slip,Blue);
                  if(OrderType()==OP_SELL)
                     OrderClose(OrderTicket(),OrderLots(),Ask,slip,Red);
                 }
               Sleep(1000);
              }
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         int OpenPendingOrder(int pType,double pLots,double pLevel,int sp, double pr, int sl, int tp,string pComment,int pMagic,datetime pExpiration,color pColor)
           {
            int ticket=0;
            int err=0;
            int c=0;
            int NumberOfTries=100;
            switch(pType)
              {
               case OP_BUYLIMIT:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     ticket=OrderSend(Symbol(),OP_BUYLIMIT,pLots,pLevel,sp,StopLong(pr,sl),TakeLong(pLevel,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(1000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
               case OP_BUYSTOP:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     ticket=OrderSend(Symbol(),OP_BUYSTOP,pLots,pLevel,sp,StopLong(pr,sl),TakeLong(pLevel,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(5000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
               case OP_BUY:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     RefreshRates();
                     ticket=OrderSend(Symbol(),OP_BUY,pLots,Ask,sp,StopLong(Bid,sl),TakeLong(Ask,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(5000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
               case OP_SELLLIMIT:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     ticket=OrderSend(Symbol(),OP_SELLLIMIT,pLots,pLevel,sp,StopShort(pr,sl),TakeShort(pLevel,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(5000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
               case OP_SELLSTOP:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     ticket=OrderSend(Symbol(),OP_SELLSTOP,pLots,pLevel,sp,StopShort(pr,sl),TakeShort(pLevel,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(5000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
               case OP_SELL:
                  for(c=0;c < NumberOfTries;c++)
                    {
                     ticket=OrderSend(Symbol(),OP_SELL,pLots,Bid,sp,StopShort(Ask,sl),TakeShort(Bid,tp),pComment,pMagic,pExpiration,pColor);
                     err=GetLastError();
                     if(err==0)
                       {
                        break;
                       }
                     else
                       {
                        if(err==4 || err==137 ||err==146 || err==136) //Busy errors
                          {
                           Sleep(5000);
                           continue;
                          }
                        else //normal error
                          {
                           break;
                          }
                       }
                    }
                  break;
              }

            return(ticket);
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double StopLong(double price,int stop)
           {
            if(stop==0)
               return(0);
            else
               return(price-(stop*Point));
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double StopShort(double price,int stop)
           {
            if(stop==0)
               return(0);
            else
               return(price+(stop*Point));
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double TakeLong(double price,int take)
           {
            if(take==0)
               return(0);
            else
               return(price+(take*Point));
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double TakeShort(double price,int take)
           {
            if(take==0)
               return(0);
            else
               return(price-(take*Point));
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double CalculateProfit()
           {
            double Profit=0;
            for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
              {
               OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
               if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
                  continue;
               if(OrderSymbol()==Symbol() && OrderMagicNumber()==MagicNumber)
                  if(OrderType()==OP_BUY || OrderType()==OP_SELL)
                    {
                     Profit=Profit+OrderProfit();
                    }
              }
            return(Profit);
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         void TrailingAlls(int start,int stop, double AvgPrice)
           {
            int profit;
            double stoptrade;
            double stopcal;
            if(stop==0)
               return;
            int trade;
            for(trade=OrdersTotal()-1;trade>=0;trade--)
              {
               if(!OrderSelect(trade,SELECT_BY_POS,MODE_TRADES))
                  continue;
               if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
                  continue;
               if(OrderSymbol()==Symbol()||OrderMagicNumber()==MagicNumber)
                 {
                  if(OrderType()==OP_BUY)
                    {
                     profit=NormalizeDouble((Bid-AvgPrice)/Point,0);
                     if(profit<start)
                        continue;
                     stoptrade=OrderStopLoss();
                     stopcal=Bid-(stop*Point);
                     if(stoptrade==0||(stoptrade!=0&&stopcal>stoptrade))
                        //     OrderModify(OrderTicket(),OrderOpenPrice(),stopcal,OrderTakeProfit(),0,Blue);
                        OrderModify(OrderTicket(),AvgPrice,stopcal,OrderTakeProfit(),0,Aqua);
                    }//Long
                  if(OrderType()==OP_SELL)
                    {
                     profit=NormalizeDouble((AvgPrice-Ask)/Point,0);
                     if(profit<start)
                        continue;
                     stoptrade=OrderStopLoss();
                     stopcal=Ask+(stop*Point);
                     if(stoptrade==0||(stoptrade!=0&&stopcal<stoptrade))
                        //     OrderModify(OrderTicket(),OrderOpenPrice(),stopcal,OrderTakeProfit(),0,Red);
                        OrderModify(OrderTicket(),AvgPrice,stopcal,OrderTakeProfit(),0,Red);
                    }//Shrt
                 }
               Sleep(1000);
              }//for
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double AccountEquityHigh()
           {
            static double AccountEquityHighAmt,PrevEquity;
            if(CountTrades()==0) AccountEquityHighAmt=AccountEquity();
            if(AccountEquityHighAmt < PrevEquity) AccountEquityHighAmt=PrevEquity;
            else AccountEquityHighAmt=AccountEquity();
            PrevEquity=AccountEquity();
            return(AccountEquityHighAmt);
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double FindLastBuyPrice()
           {
            double oldorderopenprice=0, orderprice;
            int cnt, oldticketnumber=0, ticketnumber;
            for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
              {
               OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
               if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
                  continue;
               if(OrderSymbol()==Symbol() && OrderMagicNumber()==MagicNumber && OrderType()==OP_BUY)
                 {
                  ticketnumber=OrderTicket();
                  if(ticketnumber>oldticketnumber)
                    {
                     orderprice=OrderOpenPrice();
                     oldorderopenprice=orderprice;
                     oldticketnumber=ticketnumber;
                    }
                 }
              }
            return(orderprice);
           }
//+------------------------------------------------------------------+
//|                                                                  |
//+------------------------------------------------------------------+
         double FindLastSellPrice()
           {
            double oldorderopenprice=0, orderprice;
            int cnt, oldticketnumber=0, ticketnumber;
            for(cnt=OrdersTotal()-1;cnt>=0;cnt--)
              {
               OrderSelect(cnt, SELECT_BY_POS, MODE_TRADES);
               if(OrderSymbol()!=Symbol()||OrderMagicNumber()!=MagicNumber)
                  continue;
               if(OrderSymbol()==Symbol() && OrderMagicNumber()==MagicNumber && OrderType()==OP_SELL)
                 {
                  ticketnumber=OrderTicket();
                  if(ticketnumber>oldticketnumber)
                    {
                     orderprice=OrderOpenPrice();
                     oldorderopenprice=orderprice;
                     oldticketnumber=ticketnumber;
                    }
                 }
              }
            return(orderprice);
           }
//+------------------------------------------------------------------+


int getSignal(){
   int score =  macd() ;
   int bb = bollinger();
   
 //if(bb != 0 ) return (bb); 
 //  return (score);
   
   
   if(score > 1 && ichi() > 0 && bb >= 0 )
   return ( 1);
   else if (score < -1 && ichi() < 0 && bb <= 0) return (-1);
   return (0);
}



double macd()
  {
 
    double stoch = iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_MAIN,0);
   int stochsig = 0;
   if(stoch  > iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_SIGNAL,0) && stoch < 90)
    stochsig = 1;
   if(stoch  < iStochastic(NULL,0,10,3,3,MODE_SMA,0,MODE_SIGNAL,0) && stoch > 10)   
    stochsig = -1;
  
   int score = 0;
  // score += macdScore(PERIOD_M1);
   score += macdScore(PERIOD_M5);
   score += macdScore(PERIOD_M15);
   
   if(score >= 1 ){
    Comment ("Trend is Strongly Bullish ");
    return (1);
   }
   
   if(score <= -1 ) {
       Comment ("Trend is Strongly Bearish ");
       return (-1);
   }
   
   if(score == 1 ){
       Comment ("Trend is Mildly Bullish ");
   }
   if(score == -1 ){
       Comment ("Trend is Mildly Bearish ");
   }
   
   
   return (0);
 
   }
   
 int macdScore(int period){
    // int period = 0;
    string indName = "ZMA";
   
  
  /*
    double one = iCustom(NULL, period, indName,0,0);
    double two = iCustom(NULL, period, indName,0,1);
    double three = iCustom(NULL, period, indName,0,2);
  */
   
   double one = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,1);
   double two = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,2);
   double three = iMACD(Symbol(),period,dMacd1,dMacd2,dMacd3,PRICE_CLOSE,MODE_MAIN,3);
  
 
  
   if(one > two &&  two > three ) return (1);
   else if (two > one &&  three > two ) return (-1);
  
   return (0);
 }  
  
  int timeCheck(){
      datetime current = TimeCurrent();
     if( TimeDayOfWeek(current) == 5 )
        Comment("It is Friday today - not trading");
       
       
     int begin = GmtBeginHour;
     int end  = 24 + GmtEndHour;  
     int gmt = getGmtHour();  
     if(gmt <  GmtEndHour)
         gmt += 24;
  
     if(gmt >= begin &&  gmt <= end ){
         Comment(" Trading now GMT: " + gmt );
     }
     else{
         Comment("Now is: " + gmt + ":00 Trading will begin at " + begin);
         return (0); 
     }
 
  }
 


extern int    x1 = 141;
extern int    x2 = 161;
extern int    x3 = 197;
extern int    x4 = 96;

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