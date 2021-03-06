package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Vector;

import org.sat4j.core.VecInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class MeansEndsLinkAxioms extends LinkAxioms {

	public MeansEndsLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		//System.out.println("creating new MeansEndsLinkAxioms");
	}
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Means-Ends");
		findIndexes();
		
		//Forward:
		//(For all i  or S(ei)) -> S(e)
		forwardClauses.addAll(addOrImplication(sourceIndexes, tIndex));
		
		//(For all i or PS(ei)) -> PS(e)
		VecInt sourceIndexes2 = incrementAll(sourceIndexes, 1);
		forwardClauses.addAll(addOrImplication(sourceIndexes2, tIndex + 1));
		
		//((for all i or U(ei)) and (for all k not i not PS(ek)) -> U(e)
		//int [] ar = {1};
		forwardClauses.addAll(allButImplicationForward(2, 1, 1, -1));
		
		//((for all i or C(ei)) and (for all k not i  PD(ek)) -> C(e)
		//int [] ar2 = {4};
		forwardClauses.addAll(allButImplicationForward(3, 1, 4, 1));
		
		//(for all i or PD(ei) -> PD(e)
		VecInt sourceIndexes3 = incrementAll(sourceIndexes, 4);
		forwardClauses.addAll(addAndImplication(sourceIndexes3, tIndex + 4));
		
		//(for all i or D(ei) -> D(e)
		//it = sourceIndexes.iterator();
		//while (it.hasNext()) {
		VecInt sourceIndexes4 = incrementAll(sourceIndexes, 5);
		forwardClauses.addAll(addAndImplication(sourceIndexes4, tIndex + 5));
		//}
	}
	
	
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Means-Ends");
		findIndexes();
		
		//Backward:
		//S(e) -> (For all i  and S(ei))
		backwardClauses.addAll(addOrImplication(tIndex, sourceIndexes));
		
		//(For all i and PS(ei)) -> PS(e)
		VecInt sourceIndexes2 = incrementAll(sourceIndexes, 1);
		backwardClauses.addAll(addOrImplication(tIndex + 1, sourceIndexes2));
		
		//U(e) -> ((for all i or U(ei)) and (for all k not i not PS(ek))
		//int [] ar = {3,4};
		backwardClauses.addAll(allButImplicationBackward(2, 1, 1, -1));

		//((for all i or C(ei)) and (for all k not i  PD(ek)) -> C(e)
		//t [] ar2 = {4};
		backwardClauses.addAll(allButImplicationBackward(3, 1, 4, 1));
		
		//(for all i or PD(ei) -> PD(e)
		VecInt sourceIndexes3 = incrementAll(sourceIndexes, 4);
		backwardClauses.addAll(addAndImplication(tIndex + 4, sourceIndexes3));
				
		//(for all i or D(ei) -> D(e)
		VecInt sourceIndexes4 = incrementAll(sourceIndexes, 5);
		backwardClauses.addAll(addAndImplication(tIndex + 5, sourceIndexes4));
		
	}

}
