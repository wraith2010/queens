package com.ten31f.queens.v1.ai;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractPlayer {

	private long n = 0;

	private Random random;

}
