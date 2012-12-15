/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.util.test.concurrent;


/**
 * Абстракция описывающая гонку потоков.
 * В гонку включаются тред-таски
 * Каждая из которых стартует с определенного стринг-события,
 * может уснуть кинув стринг-событие
 * и проснуться на стринг-событие
 * 
 * Это выглядит как:
 * - первый поток начал работу с начала,
 * - вызвал метод А
 * - кинул событие "первый поток вызвал А" и задержался
 * - второй поток начал с "первый поток кинул А"
 * - второй поток вызвал метод Б
 * - второй поток кинул событие "второй поток вызвал метод Б" и закончился
 * - первый поток продолжил с "второй поток вызвал метод Б"
 * - первый поток проверил состояние и закончился
 *
 */
public class ThreadsRace {

	
	public void startFromBegin(ThreadRacer threadRacer) {
		// TODO Auto-generated method stub
		
	}
	
	public void startWithEvent(String event, ThreadRacer threadRacer) {
		// TODO Auto-generated method stub
		
	}
	

	public void invoke() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void invokeWithRuntimeExceptions() {
		try {
			invoke();
		}catch (RuntimeException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
