public class Knoten
{
	int inhalt;
	Knoten links, rechts;
	Knoten(int findezahl)
	{
		this.inhalt = findezahl;
		this.links = null;
		this.rechts = null;
	}
} 