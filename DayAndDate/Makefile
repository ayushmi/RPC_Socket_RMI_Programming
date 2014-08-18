# Author: Ayush Mittal
# --------------------
# CS632A - Assignment1
# --------------------
# Makefile

all: rpcgen date_client

date_client: date_client.o date_clnt.o date_xdr.o
	gcc -o date_client date_client.o date_clnt.o date_xdr.o

date_client.o:
	gcc -g -DRPC_SVC_FG -c -o date_client.o date_client.c

date_clnt.o: rpcgen
	gcc -c -o date_clnt.o date_clnt.c

date_xdr.o: rpcgen
	gcc -c -o date_xdr.o date_xdr.c

rpcgen:
	rpcgen date.x

clean:
	rm -f date_client date_xdr.c date_clnt.c date.h *.o
