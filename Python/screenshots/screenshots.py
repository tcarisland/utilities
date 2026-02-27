#!/usr/local/bin/python
import os
import shutil
from datetime import datetime
from pathlib import Path

source_dir = "/Users/thorchristopher/Desktop"
base_dest_dir = "/Users/thorchristopher/Pictures/skjermbildenotater"

def get_date_info(filename):
    try:
        date_part = filename.split()[1]
        date_obj = datetime.strptime(date_part, "%Y-%m-%d")
        return {
            'year': date_obj.year,
            'week': date_obj.isocalendar()[1],
            'date_obj': date_obj
        }
    except(IndexError, ValueError):
        print("could not extract date")
        return None

def main():
    print("hello")
    png_files = [f for f in os.listdir(source_dir) if f.startswith("Screenshot") and f.endswith(".png")]
    png_files.sort()
    for file in png_files:
        print(f"file: {file}")
        date_info = get_date_info(file)
        if date_info is None:
            print(f"Skipping {file} - could not extract date")

        year_path = Path(base_dest_dir) / str(date_info['year'])
        year_path.mkdir(exist_ok=True)

        week_dir = str(date_info['week'])
        week_path = year_path / week_dir
        week_path.mkdir(exist_ok=True)

        source_file = Path(source_dir) / file
        dest_file = week_path / file

        try:
            shutil.move(str(source_file), str(dest_file))
            print(f"Moved {file} to Year {date_info['year']}, Week {date_info['week']}")
        except Exception as e:
            print(f"Error movind {file}: {str(e)}")

if __name__ == "__main__":
    main()