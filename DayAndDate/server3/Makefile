# Author: Ayush Mittal
# --------------------
# CS632A - Assignment1
# --------------------
# Makefile

all: rpcgen server3 remove

server3: server3.o date_svc.o date_xdr.o
	gcc -o server3 server3.o date_svc.o date_xdr.o

server3.o:
	gcc -g -DRPC_SVC_FG -c -o server3.o server3.c

date_svc.o: rpcgen
	gcc -c -o date_svc.o date_svc.c

date_xdr.o: rpcgen
	gcc -c -o date_xdr.o date_xdr.c

rpcgen:
	rpcgen date.x

remove:
	rm -f *.o date_xdr.c date_clnt.c date_svc.c date.h

clean:
	rm -f server date_xdr.c date_svc.c date.h *.o
