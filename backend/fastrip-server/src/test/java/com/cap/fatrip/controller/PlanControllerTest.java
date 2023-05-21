package com.cap.fatrip.controller;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


class PlanControllerTest {

	@Test
	void update() {
		Set<String> a = new HashSet<>(List.of("a", "b", "c", "d"));
		Set<String> b = new HashSet<>(List.of("b", "d", "e"));
//		a.removeAll(b);
		a.retainAll(b);
		System.out.println();
	}
}