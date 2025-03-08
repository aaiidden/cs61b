class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;
      }
    
    public Planet(Planet b) {
      xxPos = b.xxPos;
      yyPos = b.yyPos;
      xxVel = b.xxVel;
      yyVel = b.yyVel;
      mass = b.mass;
      imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet p) {
      double xxDis = p.xxPos - xxPos;
      double yyDis = p.yyPos - yyPos;
      return Math.sqrt(xxDis * xxDis + yyDis * yyDis);
    }

    public double calcForceExertedBy(Planet p) {
      return G * mass * p.mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
      double xxDis = p.xxPos - xxPos;
      return calcForceExertedBy(p) * xxDis / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
      double yyDis = p.yyPos - yyPos;
      return calcForceExertedBy(p) * yyDis / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] pList) {
      double netForceX = 0;
      for (Planet p : pList) {
      if (!equals(p)) {
          netForceX = netForceX + calcForceExertedByX(p);
        }
      }
      return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] pList) {
      double netForceY = 0;
      for (Planet p : pList) {
      if (!equals(p)) {
          netForceY = netForceY + calcForceExertedByY(p);
        }
      }
      return netForceY;
    }

    public void update(double dt, double fX, double fY) {
      double aX = fX / mass;
      double aY = fY / mass;
      xxVel = xxVel + aX * dt;
      yyVel = yyVel + aY * dt;
      xxPos = xxVel * dt + xxPos;
      yyPos = yyVel * dt + yyPos;
    }

    public void draw() {
      StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}