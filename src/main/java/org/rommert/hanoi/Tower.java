package org.rommert.hanoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A single Tower of Hanoi.
 * A Tower should never have a bigger Disk resting on a smaller Disk.
 */
public class Tower {

    private List<Integer> disks = new ArrayList<>();
    private String name;

    public Tower(String name, Integer... disk) {
        this.name = name;
        disks = new ArrayList<>(Arrays.asList(disk));
    }


    public void removeDisk(int disk) {
        if (getTopDisk() != disk) {
            throw new IllegalArgumentException(String.format("Trying to remove disk %s from tower %s but it is not the top disk", disk, this));
        }
        disks.remove(disks.size()-1);
    }

    private int getTopDisk() {
        return disks.get(disks.size()-1);
    }

    public void addDisk(int disk) {
        if (!disks.isEmpty() && getTopDisk() < disk) {
            throw new IllegalArgumentException(String.format("Trying to move disk %s to tower %s but the top disk is smaller than the disk being moved", disk, this));
        }
        disks.add(disk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tower tower = (Tower) o;

        return name.equals(tower.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name+disks;
    }


    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public int size() {
        return disks.size();
    }

    public String getName() {
        return name;
    }

    public Integer getDisk(int index) {
        return disks.get(index);
    }
}
