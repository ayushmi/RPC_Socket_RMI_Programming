#define VERSION_NUMBER 1
struct date{
	int dd;
	int mm;
	int yy;
};
program DATE_PROG{
	version SIMP_VERSION{
		int numLeapYears(date) = 1;
		int totalDaysTill(date) = 2;
		int day(date) = 3;
	}=1;
}=0x123456;
