#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<string.h>

void PrintMenu()
{
	printf("Enter 1 to serach for a book \nEnter 2 to insert a book in library \nEnter 3 to issue a book\nEnter 4 to renew a book\nEnter 5 to reserve a book\nEnter 6 to save server updates.\nEnter 0 to exit\n");
    printf("================================================\n");
}

void Search(int s)
{
	char Book[1000] = "1";
	char message[1000];
	if( send(s, Book , strlen(Book) , 0) < 0)
    {
    	printf("ERROR: Send Failed.. Try Again\n");
        return;
    }
    int r = recv(s,message,1000,0);
	printf("Enter the Book name to Search for: \n");
	scanf(" %[^\n]s",Book);
    Book[strlen(Book)] = '\0';
    if( send(s, Book , strlen(Book) , 0) < 0)
    {
    	printf("ERROR: Send Failed.. Try Again\n");
        return;
    }
    message[0] = '\0';
    r = recv(s,message,1000,0);
    message[r] = '\0';
    printf("%s\n",message);
    printf("\n");
    printf("================================================\n");
}
void Insert(int s)
{
    char Book[1000] = "2";
    char message[1000];
    if( send(s, Book , strlen(Book) , 0) < 0)
    {
        printf("ERROR: Send Failed.. Try Again\n");
        return;
    }
    int r = recv(s,message,1000,0);
    printf("Enter the Book name to Add to the library: \n");
    scanf(" %[^\n]s",Book);
    Book[strlen(Book)] = '\0';
    if( send(s, Book , strlen(Book) , 0) < 0)
    {
        printf("ERROR: Send Failed.. Try Again\n");
        return;
    }
    r=recv(s,message,1000,0);
}
void Issue(int s)
{

}
void Renew(int s)
{

}
void Reserve(int s)
{

}
void Save(int s)
{
    char Book[1000] = "6";
    char message[1000];
    if( send(s, Book , strlen(Book) , 0) < 0)
    {
        printf("ERROR: Send Failed.. Try Again\n");
        return;
    }
    int r = recv(s,message,1000,0);
    printf("Done Saving\n");
}
int main()
{
	int s; //s will store the socket descriptor

	//Create Socket,
	//--- IPV4 Family
	//--- SOCK_STREAM virtual circuit service
	//--- Default Transport Protocol (although there is only one type of virtual circuit service.)
	s = socket(AF_INET, SOCK_STREAM, 0);

	if (s<0)
	{
		printf("ERROR: Socket cannot be created. Exiting\n");
		exit(1);
	}

	//Prepare to bind address to socket s
	struct sockaddr_in server_address; 					//Specifies address family, port number and IP address.
	server_address.sin_family = AF_INET;
	server_address.sin_addr.s_addr = inet_addr("127.0.0.1");	//0.0.0.0 as the IP Address
	server_address.sin_port = htons(8223);					//This is client therefore pick any available port.

	//Connect to the server
	if (connect(s , (struct sockaddr *)&server_address , sizeof(server_address)) < 0)
    {
    	printf("ERROR: Server Connection Fail\n");
        exit(1);
    }

    char message[1000];
    int r = recv(s,message,1000,0);
    if (r<=0)
    {
    	printf("ERROR: Unknown Error occured\n");
    	exit(1);
    }
    printf("%s\n",message);

    printf("Server Coonnection Established\n");
    printf("Welcome to the Library Management Portal: CS632A\n");
    printf("================================================\n");
    int flag=1;
    while(flag==1)
    {
    	int choice=-1;
    	PrintMenu();
    	scanf("%d",&choice);
    	switch(choice)
    	{
    		case 1: Search(s); break;
    		case 2: Insert(s); break;
    		case 3: Issue(s); break;
    		case 4: Renew(s); break;
    		case 5: Reserve(s); break;
    		case 6: Save(s); break;
            case 0: flag=0; break;
    		default: printf("ERROR: Wrong Choice\n");break;  
    	}
    }
    strcpy(message,"7");
    send(s,message,strlen(message),0);
}