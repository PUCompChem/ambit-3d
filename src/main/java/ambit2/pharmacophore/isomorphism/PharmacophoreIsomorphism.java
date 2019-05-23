package ambit2.pharmacophore.isomorphism;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ambit2.pharmacophore.Pharmacophore;
import ambit2.pharmacophore.TargetPharmacophores;


public class PharmacophoreIsomorphism 
{

	
	protected Pharmacophore query = null; //(features + connections);
	protected TargetPharmacophores target; //IAtomContainer target + target-graph (feature instances + 3d distances);
	
	protected boolean isomorphismFound;
	protected boolean FlagStoreIsomorphismNode = false;
	
	/*
	protected List<Node> isomorphismNodes = new ArrayList<Node>(); 
	protected Stack<Node> stack = new Stack<Node>();
	
	protected List<IFeatureInstacne> targetFI = new ArrayList<...>(); //a work container
	protected List<FIQuerySequenceElement> sequence = new ArrayList<FIQuerySequenceElement>();
	
	??? protected List<IQueryAtom> sequencedAtoms = new ArrayList<IQueryAtom>();
	??? protected List<IQueryAtom> sequencedBondAt1 = new ArrayList<IQueryAtom>();
	??? protected List<IQueryAtom> sequencedBondAt2 = new ArrayList<IQueryAtom>();
	
	*/
	
	
	
}
