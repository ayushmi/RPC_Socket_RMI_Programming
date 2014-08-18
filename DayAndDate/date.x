/* Author: Ayush Mittal
 * ---------------------
 * CS632A - Assignment
 * ---------------------
 * Protocol Specification
*/

struct date{
	int day;
	int month;
	int year;
};

program DATEPROG1{
	version SIMP_VERSION {
		int NumberOfLeapYears(date) = 1;
	} = 1;
} = 0x5119935;	

program DATEPROG2{
	version SIMP_VERSION {
		int TotalDays(date) = 1;
	} = 1;
} = 0x5119936;

program DATEPROG3{
	version SIMP_VERSION {
		int FindDay(date) =1;
	} = 1;
} = 0x5119937;	
