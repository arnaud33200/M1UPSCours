gst-launch filesrc location=./trailer_400p.ogg ! oggdemux ! theoradec ! xvimagesink

gst-launch filesrc location=./trailer_400p.ogg ! oggdemux ! theoradec ! videoscale ! video/x-raw-yuv,heigh=40  ! xvimagesink

gst-launch filesrc location=./drlp090_d027.mp3 ! mad ! audioconvert ! audioresample ! autoaudiosink

############# SCHEMA CONVERSION OGG --> AVI #################

gsti-launch filesrc location=file.ogg ! oggdemux name=demux ! multiplexer name=mux ! filesink location=out.avi \
	demux. ! queue ! < DECODAGE -> GERERVOLUME -> ENCODAGE MP3 -> MUX. > \
	demux. ! queue ! < DECODAGE -> REDIMENSIONNEMENT -> ENCODAGE MPEG2 -> mux.



gst-launch filesrc location=trailer_400p.ogg ! oggdemux name=demux ! avimux name=mux ! filesink location=out.avi \
demux. ! queue ! vorbisdec ! audioconvert ! volume volume=0.75 ! lame ! mux. \
demux. ! queue ! theoradec ! videoscale method=0 ! video/x-raw-yuv,width=320,height=240 ! mpeg2enc ! mux.

gst-launch filesrc location=./out.avi ! avidemux name=d \
	d. ! queue ! mpeg2dec ! autovideosink \
	d. ! queue ! mad ! audioconvert ! audioresample ! autoaudiosink

