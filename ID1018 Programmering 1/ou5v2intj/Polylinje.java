public class Polylinje
{
    private Punkt[] horn;
    private String farg = "svart";
    private int bredd = 1;

    public Polylinje ()
    {
        this.horn = new Punkt[0];
    }
    public Polylinje (Punkt[] horn)
    {
        this.horn = new Punkt[horn.length];
        for (int i = 0; i < horn.length; i++)
            this.horn[i] = new Punkt (horn[i]);
    }
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append ("(");
        for(int i = 0; i <= horn.length -1;i++)
        {
            sb.append(horn[i]);
            sb.append(", ");
        }
        sb.append(")");

        return sb.toString();
    }
    public Punkt[] getHorn ()
    {
        return horn;
    }
    public String getFarg ()
    {
        return farg;
    }
    public int getBredd ()
    {
        return bredd;
    }
    public void setFarg (String farg)
    {
        this.farg = farg;
    }
    public void setBredd (int bredd)
    {
        this.bredd = bredd;
    }
    public double langd ()
    {

        double langd = 0;

        return langd;
    }
    public void laggTill (Punkt horn)
    {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int i = 0;
        for (i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        h[i] = new Punkt (horn);
        this.horn = h;
    }
    public void laggTillFramfor (Punkt horn, String hornNamn)
    {

    }
    public void taBort (String hornNamn)
    {

    }
}