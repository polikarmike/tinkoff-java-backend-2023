package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public record MyRecord(@Min(1) @Max(100)
                       int myInt, String myString,
                       Boolean myBoolean,
                       @Min(-100) @Max(100) Double myDouble) {

}
