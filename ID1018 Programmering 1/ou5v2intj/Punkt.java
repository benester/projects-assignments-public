class Punkt
{
    private int 	x;
    private int     y;
    private String name;

    public Punkt (Punkt p)
    {
        this.x = p.x;
        this.y = p.y;
        this.name = p.name;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public Punkt (String name, int x, int y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getNamn ()
    {
        return name;
    }
    public double avstand(Punkt p)
    {
        double d = 0;

        d = Math.sqrt(((p.x-this.x)*(p.x-this.x))+((p.y - this.y)*(p.y - this.y)));

        return d;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    public String toString()
    {
        String s = "(" + name + ", " + x + ", " + y + ")";

        return s;
    }
    public boolean equals (Punkt p)
    {
        boolean equal = false;
        if(this.x == p.x && this.y == p.y)
            equal = true;
        return equal;
    }

}