#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<arpa/inet.h>

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
	memset((char*)address, 0 , sizeof(address));
	server_address.sin_family = AF_INET;
	server_address.sin_addr.s_addr = htonl(INADDR_ANY);	//0.0.0.0 as the IP Address
	server_address.sin_port = htons(0);					//This is client therefore pick any available port.

	//Bind the socket to the address
	if ( bind(s,(struct sockaddr *)&server_address,sizeof(server_address)) < 0)
	{
		printf("ERROR: Binding Fail. Exiting\n");
		exit(1);
	}


	
}