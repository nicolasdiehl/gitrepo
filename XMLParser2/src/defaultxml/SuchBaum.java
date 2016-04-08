package defaultxml;
class SuchBaum
{
	SuchBaum links; 							// linker Teilbaum
	SuchBaum rechts; 							// rechter Teilbaum
	int inhalt; 								// Inhalt wenn m�glich Integer
	SuchBaum(int zahl) 							// Konstruktor
	{
		this.links = null;
		this.rechts = null;
		this.inhalt = zahl;
	}
	Knoten wurzel = null;
	void fuegeEin(int personalnummer) 			// in Baum einf�gen
	{
		if (wurzel == null)
		{
			wurzel = new Knoten(personalnummer);
		}
		else
		{
			fuegeEin(wurzel, personalnummer);
		}
	}
	void fuegeEin(Knoten ast, int personalnummer)// in Baum einf�gen
	{
		if (personalnummer < ast.inhalt) 		// links einfuegen
		{
			if (ast.links == null)
			{
				ast.links = new Knoten(personalnummer);
			}
			else
			{
				fuegeEin(ast.links, personalnummer);
			}
		}
		else if (personalnummer > ast.inhalt)	// rechts einfuegen
		{
			if (ast.rechts == null)
			{
				ast.rechts = new Knoten(personalnummer);
			}
			else
			{
				fuegeEin(ast.rechts, personalnummer);
			}
		}
	}
	void laufeDurch(Knoten ast) 				// l�uft durch �ste
	{
		if (ast != null)
		{
			laufeDurch(ast.links);
			laufeDurch(ast.rechts);
		}
	}
	void laufeDurch() // l�uft durch Wurzel
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