
struct library
{
	char name[30];
	char issue_to[20];
	long long int issue_date_JDN[11];
	long long int due_date_JDN[11];
	int reserve_flag;
};

void Search(char Book[], char * message);
int Insert(char Book[]);
int Issue(char Book[]);
int Renew(char Book[]);
int Reserve(char Book[]);
int Exit();
