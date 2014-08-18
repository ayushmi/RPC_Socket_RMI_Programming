# Author: Ayush Mittal
# --------------------
# CS632A - Assignment1
# --------------------
# Makefile

all: rpcgen server1 remove

server1: server1.o date_svc.o date_xdr.o
	gcc -o server1 server1.o date_svc.o date_xdr.o

server1.o:
	gcc -g -DRPC_SVC_FG -c -o server1.o server1.c

date_svc.o: rpcgen
	gcc -c -o date_svc.o date_svc.c

date_xdr.o: rpcgen
	gcc -c -o date_xdr.o date_xdr.c

rpcgen:
	rpcgen date.x

remove:
	rm -f *.o date_xdr.c date_clnt.c  date_svc.c date.h

clean:
	rm -f server date_xdr.c date_svc.c date.h *.o
