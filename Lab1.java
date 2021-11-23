//Arjun Kejriwal and Alexander DelFranco

public class Lab1 {

    // The variable timesCalled will always contain the number of times the button has been
    // pushed, so it will be 1 the first time this method is called, 2 the second time, etc.
    int timesCalled = 0;

    public void process(int timesCalled) {

        // get the red, green and blue pixel values of image 0 (the first image on the screen

        int[][] red = Images.getRed(0);
        int[][] blue = Images.getBlue(0);
        int[][] green = Images.getGreen(0);

        int imageHeight = red.length;
        int imageWidth = red[0].length;

        System.out.println("There are "+imageHeight+" rows and "+imageWidth +" columns in the first image");

        if (timesCalled == 1) {
            // This happens the first time the button is pushed

            // declare new arrays for red, green and blue values for new image,
            // allocate new ones for blue and green (do red later)


            int[][] newblue = new int[imageHeight][imageWidth];
            int[][] newgreen = new int[imageHeight][imageWidth];
            int[][] newred;

            for(int row = 0; row< imageHeight ; row++) {
                for (int column= 0; column < imageWidth; column++) {
                    newgreen[row][column] = green[row][column];
                    newblue[row][column] = blue[row][column];

                }

            }

            newred = tintRed(red, imageHeight, imageWidth);


            redStripe(imageWidth, newblue, newgreen, newred);

            for (int col = 0; col < imageWidth; col++)
            {
                newgreen[19][col] = 0;
                newblue[19][col] = 0;
                newred[19][col] = 0;
            }

            for (int row = 0; row < imageHeight; row++)
            {
                newgreen[row][9] = 255;
                newblue[row][9] = 255;
                newred[row][9] = 255;
            }

            // add this new image as image 1 on the screen (the second image)
            Images.setImage(newred, newgreen, newblue,1);
        }
        else if (timesCalled == 2) {
            // code for timesCalled == 2 (second button push)
            // The following code converts the image into a greyscale version of itself.
            // In other words, it implements a greyscale filter on the image once the button is pressed the second time
            // The original image is in slot 2 and the greyscale version of it is in slot 3

            Images.setImage("IMG4.jpg",2);

            int[][] red2 = Images.getRed(2);
            int[][] blue2 = Images.getBlue(2);
            int[][] green2 = Images.getGreen(2);

            int image2Height = red2.length;
            int image2Width = red2[0].length;

            System.out.println("There are "+image2Height+" rows and "+image2Width +" columns in the second image");

            int[][] newblue = new int[image2Height][image2Width];
            int[][] newgreen = new int[image2Height][image2Width];
            int[][] newred = new int[image2Height][image2Width];

            int[][] gray = new int[imageHeight][imageWidth];
            gray = grayscale(red2, green2, blue2, image2Height, image2Width);

            for(int row = 0; row < image2Height ; row++)
            {
                for (int column= 0; column < image2Width; column++)
                {
                    newred[row][column] = gray[row][column];
                    newgreen[row][column] = gray[row][column];
                    newblue[row][column] = gray[row][column];
                }
            }

            Images.setImage(newred, newgreen, newblue,3);

        }
        else {
            // code for timesCalled greater than 2
            // The following code shrinks the initial image (to the far left) by a factor of 2
            // The shrunk image is in slot 4

            Images.setImage("IMG1.jpg",4);

            int[][] newblue = new int[imageHeight/2][imageWidth/2];
            int[][] newgreen = new int[imageHeight/2][imageWidth/2];
            int[][] newred = new int[imageHeight/2][imageWidth/2];

            newred = shrinkRed(red, imageHeight, imageWidth);
            newgreen = shrinkGreen(green, imageHeight, imageWidth);
            newblue = shrinkBlue(blue, imageHeight, imageWidth);

            Images.setImage(newred, newgreen, newblue,4);
        }
    }

    private void redStripe(int imageWidth, int[][] newblue, int[][] newgreen, int[][] newred) {
        for(int row = 100; row< 120 ; row++) {
            for (int column= 0; column < imageWidth; column++) {
                newred[row][column] = 255;
                newgreen[row][column] = 0;
                newblue[row][column] = 0;
            }
        }
    }

    private int[][] tintRed(int[][] red, int imageHeight, int imageWidth) {
        int[][] newred = new int[imageHeight][imageWidth];
        for(int row = 0; row< imageHeight ; row++) {
            for (int column= 0; column < imageWidth; column++) {
                newred[row][column] = red[row][column]+(255-red[row][column])/2;
            }
        }
        return newred;
    }

    private int[][] grayscale(int[][] red, int[][] green, int[][] blue, int imageHeight, int imageWidth)
    {
        int[][] gray = new int[imageHeight][imageWidth];
        for(int row = 0; row< imageHeight ; row++) {
            for (int column= 0; column < imageWidth; column++) {
                gray[row][column] = (red[row][column] + green[row][column] + blue[row][column])/3;
            }
        }
        return gray;
    }

    private int[][] shrinkRed(int[][] red, int imageHeight, int imageWidth)
    {
        int[][] newRed = new int[imageHeight/2][imageWidth/2];
        for(int row = 0; row< imageHeight/2 ; row++)
        {
            for (int column= 0; column < imageWidth/2; column++)
            {
                newRed[row][column] = red[2*row][2*column];
            }
        }
        return newRed;
    }

    private int[][] shrinkGreen(int[][] green, int imageHeight, int imageWidth)
    {
        int[][] newGreen = new int[imageHeight/2][imageWidth/2];
        for(int row = 0; row< imageHeight/2 ; row++)
        {
            for (int column= 0; column < imageWidth/2; column++)
            {
                newGreen[row][column] = green[2*row][2*column];
            }
        }
        return newGreen;
    }

    private int[][] shrinkBlue(int[][] blue, int imageHeight, int imageWidth)
    {
        int[][] newBlue = new int[imageHeight/2][imageWidth/2];
        for(int row = 0; row< imageHeight/2 ; row++)
        {
            for (int column= 0; column < imageWidth/2; column++)
            {
                newBlue[row][column] = blue[2*row][2*column];
            }
        }
        return newBlue;
    }
}