package uk.co.marcoratto.j2me.moonphase;

import uk.co.marcoratto.j2me.util.MyMath;
import java.util.*;

public class TestMoonPhase {

	 String versStr;
    MoonPhase myMoonPhase;
    String Str[];
    double jde[];
	 int locOffset;
    Calendar dat;
	 double currentJD;
    double currentTime;
    double T[];
    double phase;
        
  public TestMoonPhase() {
		  Str = new String[70];
        jde = new double[70];
		  T = new double[70];

		Calendar theDate = Calendar.getInstance();
        theDate.set(Calendar.DAY_OF_MONTH, 1);
        theDate.set(Calendar.MONTH, 0);
        // int offset1 = -theDate.getTimezoneOffset() / 60;
        int offset1 = - TimeZone.getDefault().getRawOffset() / 60;
        
        theDate.set(Calendar.DAY_OF_MONTH, 1);
        theDate.set(Calendar.MONTH, 7);
        // int offset2 = - theDate.getTimezoneOffset() / 60;
        
        int offset2 = - TimeZone.getDefault().getRawOffset() / 60;
        if(offset2 > offset1)
            locOffset = offset1;
        else
            locOffset = offset2;

  }

  private void test1(int year) {
        dat = Calendar.getInstance();
        dat.set(Calendar.YEAR, year - 1900);
        currentTime = (double)dat.get(Calendar.HOUR_OF_DAY) + (double)dat.get(Calendar.MINUTE) / 60D + (double)dat.get(Calendar.SECOND) / 3600D;
        currentJD = JD(dat.get(Calendar.DAY_OF_MONTH), dat.get(Calendar.MONTH) + 1, year, currentTime);
        String s = "";
        int j = 0;
        int nNewMoon = 0;
        double YEAR = (double)year - 0.10000000000000001D;
        int kStart = (int)MyMath.round((YEAR - 2000D) * 12.368499999999999D);
        for(int i = 0; i < 15; i++) {
            myMoonPhase = new MoonPhase(locOffset, false, kStart + i);
            Str[j] = myMoonPhase.newMoonStr();
            jde[j] = myMoonPhase.jdeTime();
            T[i] = myMoonPhase.newMoonJD();
            if(i > 0) {
                s = "  " + (double)MyMath.round(1000D * (T[i] - T[i - 1])) / 1000D + "d  (";
                double Day = T[i] - T[i - 1];
                Day -= 29.530588999999999D;
                double Hour = (Day - (double)(int)Day) * 24D;
                double Min = (Hour - (double)(int)Hour) * 60D;
                if(Day >= 0.0D)
                    s = s + "+" + (int)Hour + "h ";
                else
                    s = s + "-" + Math.abs((int)Hour) + "h ";
                s = s + Math.abs(MyMath.round(Min)) + "min)";
            }
            if(i > 0)
                Str[j] = Str[j] + s;

            if(T[i] < currentJD) {
                phase = currentJD - myMoonPhase.newMoonJD();
                nNewMoon = i;
            }
            Str[j + 1] = myMoonPhase.firstQuarterStr();
            jde[j + 1] = myMoonPhase.jdeTime();
            Str[j + 2] = myMoonPhase.fullMoonStr();
            jde[j + 2] = myMoonPhase.jdeTime();
            Str[j + 3] = myMoonPhase.lastQuarterStr();
            jde[j + 3] = myMoonPhase.jdeTime();

				System.out.println(Str[j]);
				System.out.println(Str[j + 1]);
				System.out.println(Str[j + 2]);
				System.out.println(Str[j + 3]);
            j += 4;
        }

  }  

    public double JD(int date, int month, int year, double STD)
    {
        double A = 10000D * (double)year + 100D * (double)month + (double)date;
        if(month <= 2)
        {
            month += 12;
            year--;
        }
        double B = (year / 400 - year / 100) + year / 4;
        A = 365D * (double)year - 679004D;
        double MJD = A + B + (double)(int)(30.600100000000001D * (double)(month + 1)) + (double)date + STD / 24D;
        return MJD + 2400000.5D;
    }

  public static void main(String args[]) {
		TestMoonPhase test = new TestMoonPhase();
		test.test1(Integer.parseInt(args[0], 10));
  }

}
