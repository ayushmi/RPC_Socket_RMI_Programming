# Author: Ayush Mittal
# --------------------
# CS632A - Assignment1
# --------------------
# Makefile

all: rpcgen server client

client: date2_client.o date2_clnt.o date2_xdr.o
	gcc -o date2_client date2_client.o date2_clnt.o date2_xdr.o

date2_client.o:
	gcc -g -DRPC_SVC_FG -c -o date2_client.o date2_client.c

server: date2_server.o date2_svc.o date2_xdr.o
	gcc -o server date2_server.o date2_svc.o date2_xdr.o

date2_server.o:
	gcc -c -o date2_server.o date2_server.c

date2_clnt.o: rpcgen
	gcc -c -o date2_clnt.o date2_clnt.c

date2_xdr.o: rpcgen
	gcc -c -o date2_xdr.o date2_xdr.c

date2_svc.o: rpcgen
	gcc -c -o date2_svc.o date2_svc.c

rpcgen:
	rpcgen date2.x

clean:
	rm -f client server date2_xdr.c date2_clnt.c date2_svc.c date2.h *.o