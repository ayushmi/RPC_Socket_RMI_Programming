
struct library
{
	char name[30];
	int issued_flag;
	int reserve_flag;
	struct library *next;
	int bookid;
};

void Search(char Book[], char * message, struct library* myLibrary, int *nbooks);
struct library* Insert(char Book[], struct library* myLibrary, int *nbooks);
int Issue(char Book[], struct library* myLibrary, int *nbooks);
int Renew(char Book[], struct library* myLibrary, int *nbooks);
int Reserve(char Book[], struct library* myLibrary, int *nbooks);
int Exit(struct library* myLibrary, int *nbooks);
