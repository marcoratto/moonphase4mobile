package uk.co.marcoratto.j2me.moonphase;

import uk.co.marcoratto.j2me.util.MyMath;

class MoonPhase {

	public MoonPhase(int locOffset, boolean isDemo, int kMoon) {
		myLocOffset = locOffset;
		demo = isDemo;
		kStart = kMoon;
	}

	public String newMoonStr() {
		k = kStart;
		T = k / 1236.85D;
		E = 1.0D - 0.002516D * T - 7.4000000000000003E-06D * T * T;
		SunMoon(T, k);
		jde = JDE(T, k) + newMoon() + planets(T, k);
		newMoonTime = jde;
		time = jde;
		caldat(jde);
		return yearStr + ", " + dayNameStr + " " + monthStr + " " + dayStr
				+ ", " + hourStr + " New Moon";
	}

	public String firstQuarterStr() {
		k = (double) kStart + 0.25D;
		T = k / 1236.85D;
		E = 1.0D - 0.002516D * T - 7.4000000000000003E-06D * T * T;
		SunMoon(T, k);
		jde = JDE(T, k) + quarterMoon() + quarter() + planets(T, k);
		time = jde;
		caldat(jde);
		return yearStr + ", " + dayNameStr + " " + monthStr + " " + dayStr
				+ ", " + hourStr + " First Quarter";
	}

	public String fullMoonStr() {
		k = (double) kStart + 0.5D;
		T = k / 1236.85D;
		E = 1.0D - 0.002516D * T - 7.4000000000000003E-06D * T * T;
		SunMoon(T, k);
		jde = JDE(T, k) + fullMoon() + planets(T, k);
		time = jde;
		caldat(jde);

		// MoonDistance md = new MoonDistance(jde);
		// return "Full Moon " + yearStr + ", " + dayNameStr + " " + monthStr +
		// " " + dayStr + ", " + hourStr + " " + MyMath.round(md.computeR()) + "
		// km";
		return yearStr + ", " + dayNameStr + " " + monthStr + " " + dayStr
				+ ", " + hourStr + " Full Moon";
	}

	public String lastQuarterStr() {
		k = (double) kStart + 0.75D;
		T = k / 1236.85D;
		E = 1.0D - 0.002516D * T - 7.4000000000000003E-06D * T * T;
		SunMoon(T, k);
		jde = ((JDE(T, k) + quarterMoon()) - quarter()) + planets(T, k);
		time = jde;
		caldat(jde);
		return yearStr + ", " + dayNameStr + " " + monthStr + " " + dayStr
				+ ", " + hourStr + " Last Quarter";
	}

	public double JDE(double T, double k) {
		return 2451550.0976499999D
				+ 29.530588853000001D
				* k
				+ (0.0001337D - (1.4999999999999999E-07D - 7.2999999999999996E-10D * T)
						* T) * T * T;
	}

	public void SunMoon(double T, double k) {
		M = (2.5533999999999999D + 29.105356690000001D * k)
				- (2.1800000000000001E-05D + 1.1000000000000001E-07D * T) * T
				* T;
		M = M % 360D;
		if (M < 0.0D)
			M = M + 360D;
		M = M * 0.017453292519943295D;
		MM = 201.5643D + 385.81693528D * k
				+ (0.0107438D + (1.239E-05D - 5.8000000000000003E-08D * T) * T)
				* T * T;
		MM = MM % 360D;
		if (MM < 0.0D)
			MM = MM + 360D;
		MM = MM * 0.017453292519943295D;
		F = (160.71080000000001D + 390.67050274000002D * k)
				- (0.0016341000000000001D + (2.2699999999999999E-06D - 1.0999999999999999E-08D * T)
						* T) * T * T;
		F = F % 360D;
		if (F < 0.0D)
			F = F + 360D;
		F = F * 0.017453292519943295D;
		omega = (124.77460000000001D - 1.5637558D * k)
				+ (0.0020690999999999999D + 2.1500000000000002E-06D * T) * T
				* T;
		omega = omega % 360D;
		if (omega < 0.0D)
			omega = omega + 360D;
		omega = omega * 0.017453292519943295D;
	}

	public double newMoon() {
		return ((((((((((((-0.40720000000000001D * Math.sin(MM)
				+ 0.17241000000000001D * E * Math.sin(M)
				+ 0.016080000000000001D * Math.sin(2D * MM) + 0.01039D
				* Math.sin(2D * F) + 0.0073899999999999999D * E
				* Math.sin(MM - M)) - 0.0051399999999999996D * E
				* Math.sin(MM + M)) + 0.0020799999999999998D * E * E
				* Math.sin(2D * M))
				- 0.0011100000000000001D * Math.sin(MM - 2D * F) - 0.00056999999999999998D * Math
				.sin(MM + 2D * F)) + 0.00055999999999999995D * E
				* Math.sin(2D * MM + M)) - 0.00042000000000000002D * Math
				.sin(3D * MM))
				+ 0.00042000000000000002D * E * Math.sin(M + 2D * F) + 0.00038000000000000002D
				* E * Math.sin(M - 2D * F))
				- 0.00024000000000000001D
				* E
				* Math.sin(2D * MM - M)
				- 0.00017000000000000001D * Math.sin(omega) - 6.9999999999999994E-05D * Math
				.sin(MM + 2D * M))
				+ 4.0000000000000003E-05D
				* Math.sin(2D * MM - 2D * F)
				+ 4.0000000000000003E-05D
				* Math.sin(3D * M)
				+ 3.0000000000000001E-05D * Math.sin((MM + M) - 2D * F) + 3.0000000000000001E-05D * Math
				.sin(2D * MM + 2D * F)) - 3.0000000000000001E-05D * Math.sin(MM
				+ M + 2D * F)) + 3.0000000000000001E-05D * Math.sin((MM - M)
				+ 2D * F))
				- 2.0000000000000002E-05D * Math.sin(MM - M - 2D * F) - 2.0000000000000002E-05D * Math
				.sin(3D * MM + M))
				+ 2.0000000000000002E-05D * Math.sin(4D * MM);
	}

	public double fullMoon() {
		return ((((((((((((-0.40614D * Math.sin(MM) + 0.17302000000000001D * E
				* Math.sin(M) + 0.016140000000000002D * Math.sin(2D * MM)
				+ 0.01043D * Math.sin(2D * F) + 0.0073400000000000002D * E
				* Math.sin(MM - M)) - 0.0051500000000000001D * E
				* Math.sin(MM + M)) + 0.0020899999999999998D * E * E
				* Math.sin(2D * M))
				- 0.0011100000000000001D * Math.sin(MM - 2D * F) - 0.00056999999999999998D * Math
				.sin(MM + 2D * F)) + 0.00055999999999999995D * E
				* Math.sin(2D * MM + M)) - 0.00042000000000000002D * Math
				.sin(3D * MM))
				+ 0.00042000000000000002D * E * Math.sin(M + 2D * F) + 0.00038000000000000002D
				* E * Math.sin(M - 2D * F))
				- 0.00024000000000000001D
				* E
				* Math.sin(2D * MM - M)
				- 0.00017000000000000001D * Math.sin(omega) - 6.9999999999999994E-05D * Math
				.sin(MM + 2D * M))
				+ 4.0000000000000003E-05D
				* Math.sin(2D * MM - 2D * F)
				+ 4.0000000000000003E-05D
				* Math.sin(3D * M)
				+ 3.0000000000000001E-05D * Math.sin((MM + M) - 2D * F) + 3.0000000000000001E-05D * Math
				.sin(2D * MM + 2D * F)) - 3.0000000000000001E-05D * Math.sin(MM
				+ M + 2D * F)) + 3.0000000000000001E-05D * Math.sin((MM - M)
				+ 2D * F))
				- 2.0000000000000002E-05D * Math.sin(MM - M - 2D * F) - 2.0000000000000002E-05D * Math
				.sin(3D * MM + M))
				+ 2.0000000000000002E-05D * Math.sin(4D * MM);
	}

	public double quarterMoon() {
		return (((((((((((-0.62800999999999996D * Math.sin(MM) + 0.17172000000000001D
				* E * Math.sin(M)) - 0.01183D * E * Math.sin(MM + M))
				+ 0.0086199999999999992D
				* Math.sin(2D * MM)
				+ 0.0080400000000000003D
				* Math.sin(2D * F)
				+ 0.0045399999999999998D * E * Math.sin(MM - M) + 0.0020400000000000001D
				* E * E * Math.sin(2D * M))
				- 0.0018D
				* Math.sin(MM - 2D * F)
				- 0.00069999999999999999D
				* Math.sin(MM + 2D * F)
				- 0.00040000000000000002D
				* Math.sin(3D * MM) - 0.00034000000000000002D * E
				* Math.sin(2D * MM - M))
				+ 0.00032000000000000003D * E * Math.sin(M + 2D * F) + 0.00032000000000000003D
				* E * Math.sin(M - 2D * F)) - 0.00027999999999999998D * E * E
				* Math.sin(MM + 2D * M)) + 0.00027D * E * Math.sin(2D * MM + M))
				- 0.00017000000000000001D * Math.sin(omega) - 5.0000000000000002E-05D * Math
				.sin(MM - M - 2D * F)) + 4.0000000000000003E-05D * Math.sin(2D
				* MM + 2D * F)) - 4.0000000000000003E-05D * Math.sin(MM + M
				+ 2D * F))
				+ 4.0000000000000003E-05D
				* Math.sin(MM - 2D * M)
				+ 3.0000000000000001E-05D
				* Math.sin((MM + M) - 2D * F)
				+ 3.0000000000000001E-05D
				* Math.sin(3D * M)
				+ 2.0000000000000002E-05D * Math.sin(2D * MM - 2D * F) + 2.0000000000000002E-05D * Math
				.sin((MM - M) + 2D * F))
				- 2.0000000000000002E-05D * Math.sin(3D * MM + M);
	}

	public double quarter() {
		return (((0.0030599999999999998D - 0.00038000000000000002D * E
				* Math.cos(M)) + 0.00025999999999999998D * Math.cos(MM)) - 2.0000000000000002E-05D * Math
				.cos(MM - M))
				+ 2.0000000000000002E-05D
				* Math.cos(MM + M)
				+ 2.0000000000000002E-05D * Math.cos(2D * F);
	}

	public void caldat(double jd) {
		jd += (double) myLocOffset / 24D;
		double JD0 = (int) (jd + 0.5D);
		int B = (int) ((JD0 - 1867216.25D) / 36524.25D);
		double C = ((JD0 + (double) B) - (double) (B / 4)) + 1525D;
		int D = (int) ((C - 122.09999999999999D) / 365.25D);
		double E = 365D * (double) D + (double) (D / 4);
		int F = (int) ((C - E) / 30.600100000000001D);
		int day = (int) ((C - E) + 0.5D)
				- (int) (30.600100000000001D * (double) F);
		dayStr = String.valueOf(day);
		if (day < 10) {
			dayStr = " " + dayStr;			
		}
		int month = F - 1 - 12 * (F / 14);
		int year = D - 4715 - (7 + month) / 10;
		double hour = 24D * ((jd + 0.5D) - JD0);
		double diff = Math.abs(hour) - (double) (int) Math.abs(hour);
		int min = (int) MyMath.round(diff * 60D);
		if (min == 60) {
			min = 0;
			hour++;
		}
		String str;
		if (min > 9) {
			str = ":";
		} else {
			str = ":0";
		}
		hourStr = (int) hour + str + min;
		if ((int) hour < 10) {
			hourStr = " " + hourStr;
		}
		monthStr = monthArray[month - 1];
		dayNameStr = dayString(jd);
		yearStr = String.valueOf(year);
	}

	public String dayString(double jd) {
		String dayArray[] = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		long num = (long) (jd + 0.5D);
		num -= (num / 7L) * 7L;
		return dayArray[(int) num];
	}

	public double planets(double T, double k) {
		double A[] = new double[15];
		double B[] = new double[15];
		double sum = 0.0D;
		A[1] = (299.76999999999998D + 0.107408D * k) - 0.0091730000000000006D
				* T * T;
		A[2] = 251.88D + 0.016320999999999999D * k;
		A[3] = 251.83000000000001D + 26.651996D * k;
		A[4] = 349.42000000000002D + 36.412478D * k;
		A[5] = 84.659999999999997D + 18.206239D * k;
		A[6] = 141.74000000000001D + 53.303770999999998D * k;
		A[7] = 207.13999999999999D + 2.453732D * k;
		A[8] = 154.84D + 7.3068600000000004D * k;
		A[9] = 34.520000000000003D + 27.261239D * k;
		A[10] = 207.19D + 0.121824D * k;
		A[11] = 291.33999999999997D + 1.844379D * k;
		A[12] = 161.72D + 24.198153999999999D * k;
		A[13] = 239.56D + 25.513099D * k;
		A[14] = 331.55000000000001D + 3.5925180000000001D * k;
		B[1] = 0.00032499999999999999D * Math.sin(0.017453292519943295D * A[1]);
		B[2] = 0.000165D * Math.sin(0.017453292519943295D * A[2]);
		B[3] = 0.000164D * Math.sin(0.017453292519943295D * A[3]);
		B[4] = 0.000126D * Math.sin(0.017453292519943295D * A[4]);
		B[5] = 0.00011D * Math.sin(0.017453292519943295D * A[5]);
		B[6] = 2.5999999999999998E-05D * Math.sin(0.017453292519943295D * A[6]);
		B[7] = 6.0000000000000002E-05D * Math.sin(0.017453292519943295D * A[7]);
		B[8] = 5.5999999999999999E-05D * Math.sin(0.017453292519943295D * A[8]);
		B[9] = 4.6999999999999997E-05D * Math.sin(0.017453292519943295D * A[9]);
		B[10] = 4.1999999999999998E-05D * Math
				.sin(0.017453292519943295D * A[10]);
		B[11] = 4.0000000000000003E-05D * Math
				.sin(0.017453292519943295D * A[11]);
		B[12] = 3.6999999999999998E-05D * Math
				.sin(0.017453292519943295D * A[12]);
		B[13] = 3.4999999999999997E-05D * Math
				.sin(0.017453292519943295D * A[13]);
		B[14] = 2.3E-05D * Math.sin(0.017453292519943295D * A[14]);
		for (int i = 1; i < 15; i++)
			sum += B[i];

		return sum;
	}

	public double newMoonJD() {
		return newMoonTime;
	}

	public double jdeTime() {
		return time;
	}

	final double K = 0.017453292519943295D;
	double M;
	double MM;
	double F;
	double omega;
	double E;
	double T;
	double jde;
	double k;
	String hourStr;
	String dayStr;
	String monthStr;
	String yearStr;
	String dayNameStr;
	int myLocOffset;
	double newMoonTime;
	double time;
	boolean demo;
	String monthArray[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" };
	int kStart;
}
