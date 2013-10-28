
########## I : MODIFICATION DU FLUX AUDIO ################

case $1 in
	"1.1")
		gst-launch filesrc location=./extrait.wav ! wavparse ! audioconvert ! audioresample ! volume volume=1 ! autoaudiosink
		;;

	"1.2")
		gst-launch filesrc location=./extrait.wav ! wavparse ! audioconvert ! audioresample ! audioecho delay=500000000000 intensity=1 ! autoaudiosink
		;;

	"1.3")
		gst-launch filesrc location=./extrait.wav ! wavparse ! audioconvert ! audioresample ! audiocheblimit mode=0 cutoff=4000 ! autoaudiosink		
		;;
	"1.4")
		gst-launch filesrc location=./extrait.wav ! wavparse ! audioconvert ! audioresample ! audiochebband mode=band-reject lower-frequency=100 upper-frequency=8000 ! autoaudiosink
		;;
esac		

########## II : MODIFICATION DU FLUX VIDEO ################

case $1 in
	"2.1")
		gst-launch-1.0 filesrc location=./2CV.jpg ! jpegdec ! imagefreeze ! videobalance saturation=0 ! autovideosink
		;;
	"2.2")
		gst-launch videotestsrc ! autovideosink
		;;
	"2.3")
		gst-launch videotestsrc ! videocrop top=180 left=242 right=0 bottom=0 ! autovideosink
		;;	
	"2.4")
		gst-launch filesrc location=../TP3/trailer_400p.ogg ! oggdemux ! theoradec ! ffmpegcolorspace ! edgetv ! ffmpegcolorspace! autovideosink
		;;
	"2.5")
		# use gamma
		gst-launch filesrc location=../TP3/trailer_400p.ogg ! oggdemux ! theoradec ! ffmpegcolorspace ! edgetv ! ffmpegcolorspace! autovideosink
		;;
	"2.6")
		# use videobalance
		gst-launch filesrc location=../TP3/trailer_400p.ogg ! oggdemux ! theoradec ! ffmpegcolorspace ! edgetv ! ffmpegcolorspace! autovideosink
		;;
	"2.7")
		# use videoflip
		gst-launch filesrc location=../TP3/trailer_400p.ogg ! oggdemux ! theoradec ! ffmpegcolorspace ! edgetv ! ffmpegcolorspace! autovideosink
		;;
esac

