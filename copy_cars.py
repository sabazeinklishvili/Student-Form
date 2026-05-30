import os
import shutil

SRC = os.path.join(os.path.dirname(__file__), "..", "assets")
DST = os.path.join(os.path.dirname(__file__), "app", "src", "main", "res", "drawable")
LOG = os.path.join(os.path.dirname(__file__), "copy_log.txt")

PAIRS = [
    (
        "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_BMW_M3-85d62904-459e-4c4b-acd3-56b1035dd00f.png",
        "car_bmw_m3_f80.png",
    ),
    (
        "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Mercedes-Benz_CLS-a76da862-8dd3-4cff-891c-a7b3f4c67dda.png",
        "car_mercedes_cls_class.png",
    ),
    (
        "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Porsche_911_GT3_rs-82db518c-c0fb-423e-8456-1c81de5d64e1.png",
        "car_porsche_911_gt3_rs.png",
    ),
    (
        "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Ferrari_488_spider-e3fa4e6e-7145-4897-9095-cc9ac98524f5.png",
        "car_ferrari_488_spider.png",
    ),
]

def main() -> None:
    os.makedirs(DST, exist_ok=True)
    lines: list[str] = []
    for src_name, dst_name in PAIRS:
        xml_path = os.path.join(DST, dst_name.replace(".png", ".xml"))
        if os.path.isfile(xml_path):
            os.remove(xml_path)
            lines.append(f"removed {xml_path}")
        src_path = os.path.join(SRC, src_name)
        dst_path = os.path.join(DST, dst_name)
        if not os.path.isfile(src_path):
            lines.append(f"MISSING {src_path}")
            continue
        shutil.copy2(src_path, dst_path)
        lines.append(f"OK {dst_name} {os.path.getsize(dst_path)} bytes")
    with open(LOG, "w", encoding="utf-8") as f:
        f.write("\n".join(lines))

if __name__ == "__main__":
    main()
