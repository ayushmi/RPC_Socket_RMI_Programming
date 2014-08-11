# Author: Ayush Mittal
# --------------------
# CS632A - Assignment1
# --------------------
# Makefile

all: rpcgen server date_client client1 client2 client3

date_client: date_client.o date_clnt.o date_xdr.o
	gcc -o date_client date_client.o date_clnt.o date_xdr.o

client1: client1.o date_clnt.o date_xdr.o
	gcc -o client1 client1.o date_clnt.o date_xdr.o

client2: client2.o date_clnt.o date_xdr.o
	gcc -o client2 client2.o date_clnt.o date_xdr.o

client3: client3.o date_clnt.o date_xdr.o
	gcc -o client3 client3.o date_clnt.o date_xdr.o

date_client.o:
	gcc -g -DRPC_SVC_FG -c -o date_client.o date_client.c

client1.o:
	gcc -c client1.c

client2.o:
	gcc -c client2.c

client3.o:
	gcc -c client3.c

server: date_server.o date_svc.o date_xdr.o
	gcc -o server date_server.o date_svc.o date_xdr.o

date_server.o:
	gcc -c date_server.c

date_clnt.o: rpcgen
	gcc -c date_clnt.c

date_xdr.o: rpcgen
	gcc -c date_xdr.c

data_svc.o: rpcgen
	gcc -c date_svc.c

rpcgen:
	rpcgen date.x

clean:
	rm -f date_client client1 client2 client3 server date_xdr.c date_clnt.c date_svc.c date.h