#!/usr/bin/perl

open($FH, "./cty.csv") || die " Couldn't open cty.csv";

while ($line = readline($FH)) {
    @fields = split(/,/, $line);
    @additional = split(/\s/, $fields[9]);
    foreach $prefix (@additional) {
        $prefix =~ s/=//;
        $prefix =~ s/\[.*\]//;
        $prefix =~ s/\;//;
        print("$fields[1],$prefix\n");
    }
}
close($FH);