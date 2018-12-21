package first;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TableauTest {
	
	
	Tableau tab = new Tableau(10, 15, 10);
	Tableau tabAff = new Tableau(10, 15, 0);

	@Test
	void testCaseQuiSeRetourne() {
		String a = tab.getCase(2, 2);
		System.out.println(a);
		tabAff.setCase(2, 2, a);
		String b = tabAff.getCase(2, 2);
		System.out.println(b);
		assertEquals(a, b);
	}


	@Test
	void testSiNombreAutourDeBombe() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<15; j++) {
				if (tab.getCase(i, j).equals("x")) {
					int cooA = i;
					int cooB = j;
					
					for (int y=-1; y<1; y++) {
						for (int z=-1; z<1; z++) {
							String caseNombre = tab.getCase(i+y, j+z);
							
							assertNotEquals(caseNombre, 0);
						}
					}
					
				}
			}
		}
	}
	


}
