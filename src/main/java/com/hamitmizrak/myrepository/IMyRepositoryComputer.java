package com.hamitmizrak.myrepository;

import java.util.List;

public interface IMyRepositoryComputer {
	
	public List<ComputerEntity> findComputerPriceMin(double computerPrice);
	
}
