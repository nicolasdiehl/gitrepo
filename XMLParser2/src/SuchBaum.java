class SuchBaum
{
	SuchBaum links; 							// linker Teilbaum
	SuchBaum rechts; 							// rechter Teilbaum
	int inhalt; 								// Inhalt wenn möglich Integer
	SuchBaum(int zahl) 							// Konstruktor
	{
		this.links = null;
		this.rechts = null;
		this.inhalt = zahl;
	}
	Knoten wurzel = null;
	void fuegeEin(int persNr) 			// in Baum einfügen
	{
		if (wurzel == null)
		{
			wurzel = new Knoten(persNr);
		}
		else
		{
			fuegeEin(wurzel, persNr);
		}
	}
	void fuegeEin(Knoten ast, int persNr)// in Baum einfügen
	{
		if (persNr < ast.inhalt) 		// links einfuegen
		{
			if (ast.links == null)
			{
				ast.links = new Knoten(persNr);
			}
			else
			{
				fuegeEin(ast.links, persNr);
			}
		}
		else if (persNr > ast.inhalt)	// rechts einfuegen
		{
			if (ast.rechts == null)
			{
				ast.rechts = new Knoten(persNr);
			}
			else
			{
				fuegeEin(ast.rechts, persNr);
			}
		}
	}
	void laufeDurch(Knoten ast) 				// läuft durch Äste
	{
		if (ast != null)
		{
			laufeDurch(ast.links);
			laufeDurch(ast.rechts);
		}
	}
	void laufeDurch() // läuft durch Wurzel
	{
		laufeDurch(wurzel);
	}
	boolean gefunden(Knoten ast, int zahl) 		// durchsucht Baum
	{
		if (ast == null)
		{
			return false;
		}
		else if (zahl == ast.inhalt)
		{
			return true;
		}
		else if (zahl < ast.inhalt)
		{
			return gefunden(ast.links, zahl);
		}
		else
		{
			return gefunden(ast.rechts, zahl);
		}
	}
	boolean gefunden(int zahl) 					// durchsucht Wurzel
	{
		return gefunden(wurzel, zahl);
	}
}