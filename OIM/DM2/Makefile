# LIBGST=$(echo $(pkg-config --cflags --libs gstreamer-0.10))
LIBGST=-pthread -I/usr/include/gstreamer-0.10 -I/usr/include/glib-2.0 -I/usr/lib/glib-2.0/include -I/usr/include/libxml2 -lgstreamer-0.10 -lgobject-2.0 -lgmodule-2.0 -pthread -lgthread-2.0 -pthread -lglib-2.0 -lxml2

all: main.o
	gcc -Wall -g $^ -o main -lncurses $(LIBGST)

main.o:
	@gcc -Wall -c main.c  $(LIBGST)

clean:
	rm main *.o