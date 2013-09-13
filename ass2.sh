#!/bin/sh

dropdb ass2
createdb ass2
psql ass2 -f ass2.sql
