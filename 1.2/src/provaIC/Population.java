

import java.util.ArrayList;
import java.util.List;


/**
 * A Population is used to hold the VertexSet
 * as well as all the paths. It is the population
 * which will be evolved and thus new paths can
 * be developed. All the genetic algorithm stuff
 * is also included in this class.
 * 
 * @author Roman
 *
 */
public class Population {

	private long nGeneration;
	private ArrayList<Path> listPaths;
	private double nMutationRate;

	private List<Vertice> grafoAtual;

	/**
	 * constructor I, used to initiate a population
	 * 
	 * @param vs            VertexSet on which the population
	 *                      should be based on
	 * @param size          number of paths created
	 * @param nMutationRate value between 0 and 1
	 *                      indicating the intensity
	 *                      of mutation being applied
	 *
	public Population(VertexSet vs, int size, double nMutationRate) {

		// define object attributes
		this.nGeneration = 0;
		this.vs = vs;
		this.nMutationRate = nMutationRate;

		// create initial paths
		this.listPaths = new ArrayList<Path>();
		for (int i = 0; i < size; i++) {
			Path p = new Path(vs.getNumberOfVertices(), true);
			this.listPaths.add(p);
		}

		// determine fitness of each path
		this.assessFitness();
	}*/

	// --------------------------------------------------

	public Population(int tamanhoPopulacao, double nMutationRate, List<Vertice> grafo) {

		this.grafoAtual = grafo;
		this.nMutationRate = nMutationRate;
		this.nGeneration = 0;
		this.listPaths = new ArrayList<Path>();

		for (int i = 0; i < tamanhoPopulacao; i++) {
			Path path = new Path(15, true); // 15 número de nós ou vertices
			path.custoPath(grafo); // calcula custo do caminho
			this.listPaths.add(path);
		}
	}

	// --------------------------------------------------

	/**
	 * Returns a Path whit a randomly defined
	 * order of how to travel the vertices.
	 * 
	 * @return a randomly defined Path
	 */
	public Path getRandomPath() {

		int nIndex = (int) (this.listPaths.size() * Math.random());
		Path aReturn = this.listPaths.get(nIndex);

		return aReturn.clone();
	}

	/**
	 * Determines the fittest path in the
	 * population by looking for the highest
	 * fitness and returns a clone of it.
	 * 
	 * @return a clone of the fittest path
	 */
	public Path getFittestPath() {

		Path aReturn = null;

		// determine fitness of each path | determinar o fitness de cada caminho
		// this.assessFitness();

		// take an assumption | supondo um caminho
		aReturn = this.listPaths.get(0);

		// check if we find a fitter path | verifique se encontramos o melhor path
		for (int i = 0; i < this.listPaths.size(); i++) {
			Path p = this.listPaths.get(i);
			if (aReturn.getFitness() > this.listPaths.get(i).getFitness()) {
				aReturn = p;
			}
		}

		return aReturn.clone();
	}

	/**
	 * Updates the fitness of each path in the
	 * population according to the total distance
	 * computable when traveling the path. Fitness
	 * is expressed as a value between 0 and 1,
	 * whereas greater values implies higher fitness
	 * and consequently a better solution.
	 * 
	 * 
	 * Atualiza a aptidão de cada caminho na população de acordo
	 * com a distância total computável ao percorrer o caminho.
	 * A aptidão é expressa como um valor entre 0 e 1,
	 * enquanto valores maiores implicam em maior aptidão
	 * e consequentemente uma melhor solução.
	 * 
	 */
	private void assessFitness() {

		double nFitnessRef = this.getPointOfReference();

		double nTotalDistance = 0;
		double nNewFitness = 0;

		// updates the fitness of each path proportionally
		// atualiza a aptidão de cada caminho proporcionalmente
		for (int i = 0; i < this.listPaths.size(); i++) {

			Path p = this.listPaths.get(i);
			// nTotalDistance = vs.getTotalDistanceWGS(p);
			nTotalDistance = 1 / p.getFitness(); // Faço essa divisão para obter um valor entre 0..1

			/*
			 * to evaluate the fitness, we must understand which
			 * scenarios are good (equals in high fitness) and which
			 * are worse (equals in low fitness). In TSP, lower
			 * total distances should be rewarded with high fitness
			 * rates, since this is object of a minimization problem.
			 * 
			 *
			 * Para avaliar a aptidão, devemos entender quais cenários
			 * são bons (iguais em alta aptidão) e quais são piores (iguais em baixa
			 * aptidão).
			 * No PCV, distâncias totais menores devem ser recompensadas
			 * com altas taxas de aptidão, uma vez que este é objeto de um problema de
			 * minimização.
			 */

			// this will reward short distances with high fitness (inversion)
			// isso recompensará distâncias curtas com alta aptidão (inversão)
			nNewFitness = (nFitnessRef - nTotalDistance) / nFitnessRef;

			// with this approach, we have to reduce the fitness by
			// Com esta abordagem, temos que reduzir a aptidão por
			// the {(number of paths in PathSet) - (1)}
			nNewFitness = nNewFitness / (this.listPaths.size() - 1);
			p.setFitness(nNewFitness);

		}

	}

	/**
	 * Sums up each total distance generated with each
	 * path in this population in order to get a point
	 * of reference.
	 * 
	 * @return sum of each path's total distance
	 *         as a double value
	 */
	private double getPointOfReference() {

		double nReturn = 0;
		double nDis = 0;

		for (int i = 0; i < this.listPaths.size(); i++) {
			Path p = this.listPaths.get(i);
			// nDis = vs.getTotalDistanceWGS(p);
			nDis = 1 / p.getFitness();
			nReturn += nDis;
		}

		return nReturn;
	}

	/**
	 * Method I used to evolve the population one
	 * generation further. Parent paths are being
	 * picked more or less randomly accordingly to
	 * their fitness.
	 */
	@Deprecated
	public void evolve() {

		ArrayList<Path> ps_new = new ArrayList<Path>();

		this.assessFitness();

		for (int i = 0; i < this.listPaths.size(); i++)
			;
		{

			Path p1 = pickOne(this.listPaths);
			Path p2 = pickOne(this.listPaths);

			Path p3 = cross(p1, p2);

			p3.mutate(this.nMutationRate);

			ps_new.add(p3);
		}

		this.listPaths = ps_new;
		this.nGeneration++;
	}

	/**
	 * Method II used to evolve the population one
	 * generation further. The two fittest paths
	 * will be the only two parent paths for all
	 * child paths of the next generation.
	 * @param list
	 */
	public void evolve2(List<Vertice> list) {
		ArrayList<Path> ps_new = new ArrayList<Path>();

		this.assessFitness();

		Path p1 = pickRank(this.listPaths, 0);
		Path p2 = pickRank(this.listPaths, 1);

		while (ps_new.size() < this.listPaths.size()) {

			Path p3 = cross(p1, p2);

			p3.mutate(this.nMutationRate);

			p3.custoPath(list);

			ps_new.add(p3);
		}

		this.listPaths = ps_new;
		this.nGeneration++;
	}

	/**
	 * Picks a path with a higher fitness more likely
	 * than a path with a smaller fitness.
	 * 
	 * @param list ArrayList of paths
	 * @return a Path
	 */
	@Deprecated
	private static Path pickOne(ArrayList<Path> list) {

		Path aReturn = null;
		int index = 0; // assumption
		double r = Math.random();

		/**
		 * imagine we would have two elements in the list.
		 * The first element has a fitness of 0.9, the
		 * second one of 0.1. Consider the fact, that the
		 * fitness indicates (rather obvious) how well an
		 * element fits. Hence we misuse this value as the
		 * probability of being picked. The random method
		 * returns a value between 0.0 and 1.0, which gets
		 * stored in the variable 'r'. Now, the probability
		 * that we will oversee the first well fitting
		 * element (e.g.: fitness of 0.9) resides by 10%,
		 * since in this case 'r' must be between 0.91 and 1.0.
		 */
		while (r > 0) {
			r = r - list.get(index).getFitness();
			index++;
		}

		// take previous one (the one which caused the loop exit)
		index--;

		// get the path
		aReturn = list.get(index);

		return aReturn;
	}

	/**
	 * Picks a path out of the provided set of paths
	 * according to the rank. The path with the highest
	 * fitness is placed on first rank (index 0).
	 * 
	 * @param list   ArrayList of paths
	 * @param nIndex index of the element in the sorted
	 *               ArrayList.
	 * @return
	 */
	private static Path pickRank(ArrayList<Path> list, int nIndex) {

		Path aReturn = null;
		ArrayList<Path> clonedList = new ArrayList<Path>(list);

		// sort paths according to their fitness in descending order (using lambda)
		clonedList.sort((p1, p2) -> Double.compare(p2.getFitness(), p1.getFitness()));

		// get the required path
		aReturn = clonedList.get(nIndex);

		return aReturn;
	}

	/**
	 * Method used to cross two paths in order
	 * to receive an even better (fitter) path.
	 * 
	 * @param p1 first parent path
	 * @param p2 second parent path
	 * @return the path resulted from the crossover
	 *         of the two parent paths
	 */
	private static Path cross(Path p1, Path p2) {

		Path aReturn = null;

		int nLength = p1.getLength();
		int nSequenceLenght = (int) (nLength * Math.random());

		// set minimum length
		if (nSequenceLenght == 0) {
			nSequenceLenght = 2;
		}

		// decrease length
		if (nSequenceLenght == nLength) {
			nSequenceLenght -= 2;
		}

		// define start index randomly
		int nStartIndex = (int) ((nLength - nSequenceLenght) * Math.random());

		// initiate a new order (child path)
		int[] order = new int[nLength];

		// put -1 into each slot as a placeholder
		for (int i = 0; i < nLength; i++) {
			order[i] = -1;
		}

		// fill in genome of first parent path
		for (int i = nStartIndex; nSequenceLenght > 0; i++) {

			order[i] = p1.get(i);
			nSequenceLenght--;
		}

		// fill in genome of second parent path
		int n = 0;
		for (int i = 0; i < nLength; i++) {
			if (order[i] == -1) {

				// fillable slot found
				boolean lExit = false;
				while (!lExit) {

					// check if vertex-index already included
					if (Path.contains(order, p2.get(n))) {
						n++;
					} else {
						lExit = true;
					}

				}

				order[i] = p2.get(n);
			}
		}

		aReturn = new Path(order);

		return aReturn;
	}

	/**
	 * Method used to get detailed information about a
	 * population in form of a multiline string.
	 * 
	 * @return Accumulates the info string of each
	 *         path stored in this population
	 *         retrieved by the Path-method
	 *         .getInfo().
	 * 
	 */
	public String getDetailedInfo() {

		String cReturn = "";

		for (int i = 0; i < this.listPaths.size(); i++) {
			Path p = this.listPaths.get(i);
			//cReturn = cReturn + p.getInfo(this.vs) + "\n";
		}

		return cReturn;
	}

	/**
	 * Method used to get information about a
	 * population in form of a one line string.
	 * 
	 * @return the populations generation number
	 *         and the current lowest distance achieved
	 *         by the population's current fittest path
	 *         as a one line info string
	 */
	public String getInfo() {

		String cReturn = "best distance in population: ";

		// double nCurrentLowestDistance =
		// this.vs.getTotalDistanceWGS(this.getFittestPath());
		// cReturn = cReturn + String.format("%.4f", nCurrentLowestDistance);

		return cReturn;
	}

}
