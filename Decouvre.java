public void Decouvre(int y, int x) {
	//Si la case est normale ou avec un ?
	if ( (jeux[y][x].getEtat() == 0 || jeux[y][x].getEtat() == 3) &&
			!jeux[y][x].estMine()) {
		nCases--; //nombre de cases non découvertes
		jeux[y][x].setEtat(1); //on indique que la case est découverte
		if (jeux[y][x].getChiffre() == 0) { // Si le nombre de mines autour est nul, on découvre les cases autour
			decouvrirPartiel1(x - 1, y - 1);
			decouvrirPartiel1(x - 1, y);
			decouvrirPartiel1(x - 1, y + 1);
			decouvrirPartiel1(x, y - 1);
			decouvrirPartiel1(x, y + 1);
			decouvrirPartiel1(x + 1, y - 1);
			decouvrirPartiel1(x + 1, y);
			decouvrirPartiel1(x + 1, y + 1);
		}
	}
	else if (n == jeux[y][x].getChiffre()) { //si il y en a autant que le nombre de mines autour, on découvre les 8 cases autour par un appel récursif de decouvre(int, int)
		if (decouvrirPartiel3(x - 1, y - 1)) decouvre(y - 1, x - 1);
		if (decouvrirPartiel3(x - 1, y)) decouvre(y, x - 1);
		if (decouvrirPartiel3(x - 1, y + 1)) decouvre(y + 1, x - 1);
		if (decouvrirPartiel3(x, y - 1)) decouvre(y - 1, x);
		if (decouvrirPartiel3(x, y + 1)) decouvre(y + 1, x);
		if (decouvrirPartiel3(x + 1, y - 1)) decouvre(y - 1, x + 1);
		if (decouvrirPartiel3(x + 1, y)) decouvre(y, x + 1);
		if (decouvrirPartiel3(x + 1, y + 1)) decouvre(y + 1, x + 1);
	}
}