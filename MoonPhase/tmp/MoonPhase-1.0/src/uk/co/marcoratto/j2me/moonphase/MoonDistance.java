package uk.co.marcoratto.j2me.moonphase;

class MoonDistance {

	MoonDistance(double JD) {
		jde = JD;
	}

	double computeR() {
		// double K = 0.017453292519943295D;
		double T = (jde - 2451545D) / 36525D;
		double D = 0.017453292519943295D * ((((297.85020420000001D + 445267.11151680001D * T) - 0.0016299999999999999D
				* T * T) + (T * T * T) / 545868D) - (T * T * T * T) / 113065000D);
		double M = 0.017453292519943295D * (((357.52910919999999D + 35999.050290899999D * T) - 0.00015359999999999999D
				* T * T) + (T * T * T) / 24490000D);
		double MS = 0.017453292519943295D * ((134.96341140000001D
				+ 477198.8676313D * T + 0.0089969999999999998D * T * T + (T * T * T) / 69699D) - (T
				* T * T * T) / 14712000D);
		double F = 0.017453292519943295D * (((93.272099299999994D + 483202.01752729999D * T)
				- 0.0034028999999999999D * T * T - (T * T * T) / 3526000D) + (T
				* T * T * T) / 863310000D);
		double E = 1.0D - 0.002516D * T - 7.4000000000000003E-06D * T * T;
		double r = ((-20905355D * Math.cos(MS) - 3699111D
				* Math.cos(2D * D - MS) - 2955968D * Math.cos(2D * D) - 569925D * Math
				.cos(2D * MS)) + E * 48888D * Math.cos(M))
				- 3149D * Math.cos(2D * F);
		r = (r + 246158D * Math.cos(2D * D - 2D * MS)) - E * 152138D
				* Math.cos(2D * D - M - MS) - 170733D * Math.cos(2D * D + MS)
				- E * 204586D * Math.cos(2D * D - M) - E * 129620D
				* Math.cos(M - MS);
		r = (r + 108743D * Math.cos(D) + E * 104755D * Math.cos(M + MS)
				+ 10321D * Math.cos(2D * D - 2D * F) + 79661D * Math.cos(MS
				- 2D * F))
				- 34782D * Math.cos(4D * D - MS) - 23210D * Math.cos(3D * MS);
		r = ((r - 21636D * Math.cos(4D * D - 2D * MS)) + E * 24208D
				* Math.cos((2D * D + M) - MS) + E * 30824D
				* Math.cos(2D * D + M))
				- 8379D * Math.cos(D - MS) - E * 16675D * Math.cos(D + M);
		r = ((r - E * 12831D * Math.cos((2D * D - M) + MS) - 10445D
				* Math.cos(2D * D + 2D * MS) - 11650D * Math.cos(4D * D)) + 14403D * Math
				.cos(2D * D - 3D * MS))
				- E * 7003D * Math.cos(M - 2D * MS);
		r = (((r + E * 10056D * Math.cos(2D * D - M - 2D * MS) + 6322D * Math
				.cos(D + MS)) - E * E * 9884D * Math.cos(2D * D - 2D * M)) + E
				* 5751D * Math.cos(M + 2D * MS))
				- E * E * 4950D * Math.cos(2D * D - 2D * M - MS);
		r = (((r + 4130D * Math.cos((2D * D + MS) - 2D * F)) - E * 3958D
				* Math.cos(4D * D - M - MS))
				+ 3258D * Math.cos(3D * D - MS) + E * 2616D
				* Math.cos(2D * D + M + MS))
				- E * 1897D * Math.cos(4D * D - M - 2D * MS);
		r = ((r - E * E * 2117D * Math.cos(2D * M - MS)) + E * E * 2354D
				* Math.cos((2D * D + 2D * M) - MS))
				- 1423D
				* Math.cos(4D * D + MS)
				- 1117D
				* Math.cos(4D * MS)
				- E
				* 1571D * Math.cos(4D * D - M);
		r = (r - 1739D * Math.cos(D - 2D * MS) - 4421D * Math.cos(2D * MS - 2D
				* F))
				+ E
				* E
				* 1165D
				* Math.cos(2D * M + MS)
				+ 8752D
				* Math.cos(2D * D - MS - 2D * F);
		return 385000.56D + r / 1000D;
	}

	double jde;
}
