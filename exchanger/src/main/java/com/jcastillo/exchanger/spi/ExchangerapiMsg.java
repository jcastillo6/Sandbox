	package com.jcastillo.exchanger.spi;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Response message structure for the webservice of io.currency
 * @author Jorge Castillo
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangerapiMsg {
	private String base;
	private String date;
	@XmlElement(name = "rates")
	private Rate rate;
	
	
	
	
	
	public String getBase() {
		return base;
	}





	public void setBase(String base) {
		this.base = base;
	}





	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
	}





	public Rate getRate() {
		return rate;
	}





	public void setRate(Rate rate) {
		this.rate = rate;
	}




	@XmlAccessorType(XmlAccessType.FIELD)
	static class Rate{
		 private double CAD;
		 private double HKD;
		 private double ISK;
		 private double PHP;
		 private double DKK;
		 private double HUF;
		 private double CZK;
		 private double AUD;
		 private double RON;
		 private double SEK;
		 private double IDR;
		 private double INR;
		 private double BRL;
		 private double RUB;
		 private double HRK;
		 private double JPY;
		 private double THB;
		 private double CHF;
		 private double SGD;
		 private double PLN;
		 private double BGN;
		 private double TRY;
		 private double CNY;
		 private double NOK;
		 private double NZD;
		 private double ZAR;
		 private double USD;
		 private double MXN;
		 private double ILS;
		 private double GBP;
		 private double KRW;
		 private double MYR;
		 private double EUR;
		 
		 
		public double getEUR() {
			return EUR;
		}
		public void setEUR(double eUR) {
			EUR = eUR;
		}
		public double getCAD() {
			return CAD;
		}
		public void setCAD(double cAD) {
			CAD = cAD;
		}
		public double getHKD() {
			return HKD;
		}
		public void setHKD(double hKD) {
			HKD = hKD;
		}
		public double getISK() {
			return ISK;
		}
		public void setISK(double iSK) {
			ISK = iSK;
		}
		public double getPHP() {
			return PHP;
		}
		public void setPHP(double pHP) {
			PHP = pHP;
		}
		public double getDKK() {
			return DKK;
		}
		public void setDKK(double dKK) {
			DKK = dKK;
		}
		public double getHUF() {
			return HUF;
		}
		public void setHUF(double hUF) {
			HUF = hUF;
		}
		public double getCZK() {
			return CZK;
		}
		public void setCZK(double cZK) {
			CZK = cZK;
		}
		public double getAUD() {
			return AUD;
		}
		public void setAUD(double aUD) {
			AUD = aUD;
		}
		public double getRON() {
			return RON;
		}
		public void setRON(double rON) {
			RON = rON;
		}
		public double getSEK() {
			return SEK;
		}
		public void setSEK(double sEK) {
			SEK = sEK;
		}
		public double getIDR() {
			return IDR;
		}
		public void setIDR(double iDR) {
			IDR = iDR;
		}
		public double getINR() {
			return INR;
		}
		public void setINR(double iNR) {
			INR = iNR;
		}
		public double getBRL() {
			return BRL;
		}
		public void setBRL(double bRL) {
			BRL = bRL;
		}
		public double getRUB() {
			return RUB;
		}
		public void setRUB(double rUB) {
			RUB = rUB;
		}
		public double getHRK() {
			return HRK;
		}
		public void setHRK(double hRK) {
			HRK = hRK;
		}
		public double getJPY() {
			return JPY;
		}
		public void setJPY(double jPY) {
			JPY = jPY;
		}
		public double getTHB() {
			return THB;
		}
		public void setTHB(double tHB) {
			THB = tHB;
		}
		public double getCHF() {
			return CHF;
		}
		public void setCHF(double cHF) {
			CHF = cHF;
		}
		public double getSGD() {
			return SGD;
		}
		public void setSGD(double sGD) {
			SGD = sGD;
		}
		public double getPLN() {
			return PLN;
		}
		public void setPLN(double pLN) {
			PLN = pLN;
		}
		public double getBGN() {
			return BGN;
		}
		public void setBGN(double bGN) {
			BGN = bGN;
		}
		public double getTRY() {
			return TRY;
		}
		public void setTRY(double tRY) {
			TRY = tRY;
		}
		public double getCNY() {
			return CNY;
		}
		public void setCNY(double cNY) {
			CNY = cNY;
		}
		public double getNOK() {
			return NOK;
		}
		public void setNOK(double nOK) {
			NOK = nOK;
		}
		public double getNZD() {
			return NZD;
		}
		public void setNZD(double nZD) {
			NZD = nZD;
		}
		public double getZAR() {
			return ZAR;
		}
		public void setZAR(double zAR) {
			ZAR = zAR;
		}
		public double getUSD() {
			return USD;
		}
		public void setUSD(double uSD) {
			USD = uSD;
		}
		public double getMXN() {
			return MXN;
		}
		public void setMXN(double mXN) {
			MXN = mXN;
		}
		public double getILS() {
			return ILS;
		}
		public void setILS(double iLS) {
			ILS = iLS;
		}
		public double getGBP() {
			return GBP;
		}
		public void setGBP(double gBP) {
			GBP = gBP;
		}
		public double getKRW() {
			return KRW;
		}
		public void setKRW(double kRW) {
			KRW = kRW;
		}
		public double getMYR() {
			return MYR;
		}
		public void setMYR(double mYR) {
			MYR = mYR;
		}
		 
		 
		
	}

}
