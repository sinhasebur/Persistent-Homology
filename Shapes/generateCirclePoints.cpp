#include <bits/stdc++.h>

using namespace std;

double PI=3.141615;

int random(double min, double max){
    int range= (int) (max-min);
    return rand()%range +min;
}


int main(){

    int numberOfCicles=1;
    int numberOfPointsPerCircle=50;
    double radiusMin=1, radiusMax=2;


    //map <int,vector<pair<double,double>>> store ();

    for(int circle=0; circle<numberOfCicles; circle++){
        double r= random(radiusMin, radiusMax);
        //double r=2;
        double k=random(0, 15);

        //cout<<"Circle_ID,X,Y,Z"<<endl;
        
        
        double tiltAngleX=random(0,360)*(PI/180);
        double tiltAngleY=random(0,360)*(PI/180);
        double tiltAngleZ=random(0,360)*(PI/180);

        //radius check
            double cx = 0.0, cy = 0.0, cz = k;
            // X rotation
            {
                double ty = cy * cos(tiltAngleX) - cz * sin(tiltAngleX);
                double tz = cy * sin(tiltAngleX) + cz * cos(tiltAngleX);
                cy = ty; cz = tz;
            }
            // Y rotation
            {
                double tx = cx * cos(tiltAngleY) + cz * sin(tiltAngleY);
                double tz = -cx * sin(tiltAngleY) + cz * cos(tiltAngleY);
                cx = tx; cz = tz;
            }
            // Z rotation
            {
                double tx = cx * cos(tiltAngleZ) - cy * sin(tiltAngleZ);
                double ty = cx * sin(tiltAngleZ) + cy * cos(tiltAngleZ);
                cx = tx; cy = ty;
            }
        
        for(int point=0; point<numberOfPointsPerCircle;point++){
            double t=random(0,360)*(PI/180);
            double x=r* cos(t);
            double y= r* sin(t);
            double z=k;

            //https://www.geeksforgeeks.org/maths/rotation-matrix/  , https://www.youtube.com/watch?v=EZufiIwwqFA&t=255s
            
            double tx, ty, tz;

            //x rotation
            tx=x; ty=y; tz=z;
            ty = y*cos(tiltAngleX) - z*sin(tiltAngleX);
            tz = y*sin(tiltAngleX) + z*cos(tiltAngleX);

            y=ty; z=tz;


            tx=x; ty=y; tz=z;
            tx = x*cos(tiltAngleY) + z*sin(tiltAngleY);
            tz = -x*sin(tiltAngleY) + z*cos(tiltAngleY);
            x=tx; z=tz;


            tx=x; ty=y; tz=z;
            tx = x*cos(tiltAngleZ) - y*sin(tiltAngleZ);
            ty = x*sin(tiltAngleZ) + y*cos(tiltAngleZ);
            x=tx; y=ty;


            //store[circle].push_back({x,y});
            //cout<<circle<<","<<x<<","<<y<<","<<z<<endl
            //cout<<x<<" "<<y<<" "<<z<<endl;
            cout<<x<<y<<z<<endl;

        }
        cout<<endl;
      
   
    }



}
