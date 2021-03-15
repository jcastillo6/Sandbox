	package com.jcastillo.exchanger.spi;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response message structure for the webservice of io.currency
 * @author Jorge Castillo
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangerapiMsg {
	private String base;
	private String date;
	private Rates rates;
	
		
	
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





	public Rates getRates() {
		return rates;
	}





	public void setRates(Rates rate) {
		this.rates = rate;
	}



	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Rates{
		@JsonProperty("CAD")
		private double CAD;
		@JsonProperty("HKD")
		private double HKD;
		@JsonProperty("ISK")
		private double ISK;
		@JsonProperty("PHP")
		private double PHP;
		@JsonProperty("DKK")
		private double DKK;
		@JsonProperty("HUF")
		private double HUF;
		@JsonProperty("CZK")
		private double CZK;
		@JsonProperty("AUD")
		private double AUD;
		@JsonProperty("RON")
		private double RON;
		@JsonProperty("SEK")
		private double SEK;
		@JsonProperty("IDR")
		private double IDR;
		@JsonProperty("INR")
		private double INR;
		@JsonProperty("BRL")
		private double BRL;
		@JsonProperty("RUB")
		private double RUB;
		@JsonProperty("HRK")
		private double HRK;
		@JsonProperty("JPY")
		private double JPY;
		@JsonProperty("THB")
		private double THB;
		@JsonProperty("CHF")
		private double CHF;
		@JsonProperty("SGD")
		private double SGD;
		@JsonProperty("PLN")
		private double PLN;
		@JsonProperty("BGN")
		private double BGN;
		@JsonProperty("TRY")
		private double TRY;
		@JsonProperty("CNY")
		private double CNY;
		@JsonProperty("NOK")
		private double NOK;
		@JsonProperty("NZD")
		private double NZD;
		@JsonProperty("ZAR")
		private double ZAR;
		@JsonProperty("USD")
		private double USD;
		@JsonProperty("MXN")
		private double MXN;
		@JsonProperty("ILS")
		private double ILS;
		@JsonProperty("GBP")
		private double GBP;
		@JsonProperty("KRW")
		private double KRW;
		@JsonProperty("MYR")
		private double MYR;
		@JsonProperty("EUR")
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
