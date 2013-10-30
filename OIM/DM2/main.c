#include <curses.h>
#include <stdio.h>
#include <gst/gst.h>
#include <string.h>


// ###############################################################
// ###############################################################

int main(int argc, char const *argv[])
{
	printf("getch : %d\n", getch());
	while (true)
	{
		int k = getch();
		switch(k)
		{
			case KEY_UP: printf("TOTO\n");
		}
	}
	GMainLoop *loop;
	// GstElement *pipelineWindow;
	// GstElement *pipelineRadio;
	// GstBus *bus;	

	gst_init (&argc, &argv);
  	loop = g_main_loop_new (NULL, FALSE);	

	return 0;
}

// ###############################################################
// ###############################################################

