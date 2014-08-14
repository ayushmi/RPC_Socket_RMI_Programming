#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <pthread.h>
#include "library.h"
#include <string.h>

// This function gets executed in each thread
void *startFunction(void *socketnumber)
{
	int s = *(int *)socketnumber;	
	char message[1000];
	
	//Send ACCEPTED to client acknowledge the client about the connection.
	strcpy(message, "ACCEPTED");
    send(s , message , strlen(message),0);

    int r;
    char query[1000];
    //Wait for client query.....
    while( (r = recv(s , query , 1000 , 0)) > 0 )
    {

    	//SEARCH for search, INSERT for insert, ISSUE for issue, RENEW for renew, RESERVE for reserve, EXIT for exit. 
    	if (query[0] == '1')
    	{
    		send(s, "1", strlen("1"),0);
    		//Receive Book Name in 
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		Search(query, message);
    	}
    	else if (query[0] == '2')
    	{
    		send(s, "1", strlen("1"),0);
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		int result = Insert(query);
    	}
    	else if (query[0] == '3')
    	{
    		send(s, "1", strlen("1"),0);
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		int result = Issue(query);
    	}
    	else if (query[0] == '4')
    	{
    		send(s, "1", strlen("1"),0);
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		int result = Renew(query);
    	}
    	else if (query[0] == '5')
    	{
    		send(s, "1", strlen("1"),0);
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		int result = Reserve(query);
    	}
    	else if (query[0] == '6')
    	{
    		send(s, "1", strlen("1"),0);
    		r = recv(s,query,1000,0);
    		if (r==0 || r==-1)
    		{
    			break;
    		}
    		int result = Exit(query);
            break;
    	}
    }
     
    if(r == 0)
    {
        printf("Client %d disconnected\n",s );
    }
    else if(r == -1)
    {
        printf("Receive Failed from clien %d",s);
    }
    return(0);
}

int main(int argc, char *argv[])
{
	int s; //s will store the socket descriptor
	int port_number;

	if(argc != 2)
    {
        printf("\n Usage: %s <port_number> \n",argv[0]);
        return 1;
    } 

    port_number = atoi(argv[1]);

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
	server_address.sin_addr.s_addr = htonl(INADDR_ANY);	//0.0.0.0 as the IP Address
	server_address.sin_port = htons(port_number);

	//Bind the socket to the address
	if ( bind(s,(struct sockaddr *)&server_address,sizeof(server_address)) < 0)
	{
		printf("ERROR: Binding Fail. Exiting\n");
		exit(1);
	}

	//Start Listening
	listen(s,5); //queue size is 5.

	//Prepare to start accepting connections
	int clientSize = sizeof(struct sockaddr_in);
	int clientsocket;
	int *socketnumber;
	struct sockaddr_in client_address;
	while(1)
	{
		clientsocket = accept(s,(struct sockaddr *)&client_address, &clientSize);
		if (clientSize<0)
		{
			printf("ERROR: Accept failed\n");
			continue;
		}
		printf("Success: Connection Accepted\n");
		socketnumber = &clientsocket;

		pthread_t thread;
		if(pthread_create(&thread, NULL, startFunction, (void *)socketnumber)<0)
		{
			printf("ERROR: Thread cannot be created\n");
			exit(1);
		}
	}
}