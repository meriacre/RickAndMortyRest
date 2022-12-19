package md.merit.rickandmortyrest.data

//class DBHandler(
//    context: Context,
//    name: String?,
//    factory: SQLiteDatabase.CursorFactory?,
//    version: Int
//) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
//
//    companion object {
//
//        private val DATABASE_NAME = "MyData.db"
//        private val DATABASE_VERSION = 2
//
//        const val CHARACTER_TABLE_NAME = "Character"
//        const val EPISODE_TABLE_NAME = "Episode"
//
//        const val COLUMN_CHARACTERIDDB = "characteriddb"
//        const val COLUMN_CHARACTERID = "characterid"
//        const val COLUMN_CHARACTERNAME = "charactername"
//        const val COLUMN_CHARACTERLOCATION = "characterlocation"
//        const val COLUMN_CHARACTEREPISODE = "characterepisode"
//        const val COLUMN_CHARACTERSTATUS = "characterstatus"
//        const val COLUMN_CHARACTERIMAGE = "characterimage"
//        const val COLUMN_CHARACTEREPISODENAME = "characterepisodename"
//
//        const val COLUMN_EPISODEIDDB = "episodeiddb"
//        const val COLUMN_EPISODEID = "episodeid"
//        const val COLUMN_EPISODENAME = "episodename"
//        const val COLUMN_EPISODELINK = "episodelink"
//
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val CREATE_CHARACTER_TABLE = "CREATE TABLE $CHARACTER_TABLE_NAME (" +
//                "$COLUMN_CHARACTERIDDB INTEGER PRIMARY KEY, " +
//                "$COLUMN_CHARACTERID INTEGER, " +
//                "$COLUMN_CHARACTERNAME TEXT, " +
//                "$COLUMN_CHARACTERLOCATION TEXT, " +
//                "$COLUMN_CHARACTEREPISODE TEXT, " +
//                "$COLUMN_CHARACTERSTATUS TEXT, " +
//                "$COLUMN_CHARACTEREPISODENAME TEXT, " +
//                "$COLUMN_CHARACTERIMAGE TEXT)"
//
//        val CREATE_EPISODE_TABLE = "CREATE TABLE $EPISODE_TABLE_NAME (" +
//                "$COLUMN_EPISODEIDDB INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "$COLUMN_EPISODEID TEXT, " +
//                "$COLUMN_EPISODENAME TEXT, " +
//                "$COLUMN_EPISODELINK TEXT)"
//
//
//        db?.execSQL(CREATE_CHARACTER_TABLE)
//        db?.execSQL(CREATE_EPISODE_TABLE)
//    }
//
//    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//        TODO("Not yet implemented")
//    }
//
//    fun addCharacter(context: Context, item: Character) {
//        val values = ContentValues()
//        values.put(COLUMN_CHARACTERID, item.id)
//        values.put(COLUMN_CHARACTERNAME, item.name)
//        values.put(COLUMN_CHARACTERLOCATION, item.location)
//        values.put(COLUMN_CHARACTEREPISODE, item.episode)
//        values.put(COLUMN_CHARACTERSTATUS, item.status)
//        values.put(COLUMN_CHARACTERIMAGE, item.image)
//        val db = this.writableDatabase
//        val querrry =
//            "Select * from " + CHARACTER_TABLE_NAME + " where " + COLUMN_CHARACTERID + " = " + item.id
//        val cursor = db.rawQuery(querrry, null)
//        if (cursor.count <= 0) {
//            try {
//                db.insert(CHARACTER_TABLE_NAME, null, values)
//                Log.d("ARAAA", "So adaugat")
////            Toast.makeText(context, "Transaction Added!", Toast.LENGTH_SHORT).show()
//            } catch (e: Exception) {
////            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//                Log.d("ARAAA", e.toString())
//            }
//            cursor.close()
//        }
////        db.close()
//    }
//
//    fun addEpisode(item: Result) {
//        val values = ContentValues()
//        values.put(COLUMN_EPISODEID, item.id)
//        values.put(COLUMN_EPISODENAME, item.name)
//        values.put(COLUMN_EPISODELINK, item.url)
//        val db = this.writableDatabase
//        val querrry =
//            "Select * from " + EPISODE_TABLE_NAME + " where " + COLUMN_EPISODEID + " = " + item.id
//        val cursor = db.rawQuery(querrry, null)
//        if (cursor.count <= 0) {
//            try {
//                db.insert(EPISODE_TABLE_NAME, null, values)
//                Log.d("ARrrEPISODE", "So adaugat")
////            Toast.makeText(context, "Transaction Added!", Toast.LENGTH_SHORT).show()
//            } catch (e: Exception) {
////            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//                Log.d("ARAAA", e.toString())
//            }
//            cursor.close()
//        }
//    }
//
//    fun getCharacters(context: Context): ArrayList<Character> {
//        val qry = "Select * From $CHARACTER_TABLE_NAME order by $COLUMN_CHARACTERID"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(qry, null)
//        val characters = ArrayList<Character>()
//
//        if (cursor.count == 0) {
//            Log.d("Records", "No records found")
//            // Toast.makeText(context, "No records found", Toast.LENGTH_SHORT).show()
//        } else {
//            cursor.moveToFirst()
//            while (!cursor.isAfterLast) {
//                val character = Character()
//                character.id = cursor.getInt(cursor.getColumnIndex(COLUMN_CHARACTERID))
//                character.name =
//                    cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTERNAME))
//                character.location = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTERLOCATION
//                    )
//                )
//                character.episode = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTEREPISODENAME
//                    )
//                )
//                character.status = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTERSTATUS
//                    )
//                )
//                character.image =
//                    cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTERIMAGE))
//                characters.add(character)
//                cursor.moveToNext()
//            }
////            Toast.makeText(context, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
////                .show()
//        }
//        cursor.close()
//        // db.close()
//        return characters
//    }
//
//    fun getCharactersFromLocation(loc: String): ArrayList<Character> {
//        val qry =
//            "Select * From $CHARACTER_TABLE_NAME WHERE $COLUMN_CHARACTERLOCATION LIKE '%$loc%'"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(qry, null)
//        val characters = ArrayList<Character>()
//
//        if (cursor.count == 0)
//            Log.d("No records", "No records Found!") else {
//            cursor.moveToFirst()
//            while (!cursor.isAfterLast) {
//                val character = Character()
//                character.id = cursor.getInt(cursor.getColumnIndex(COLUMN_CHARACTERID))
//                character.name =
//                    cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTERNAME))
//                character.location = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTERLOCATION
//                    )
//                )
//                character.episode = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTEREPISODENAME
//                    )
//                )
//                character.status = cursor.getString(
//                    cursor.getColumnIndex(
//                        COLUMN_CHARACTERSTATUS
//                    )
//                )
//                character.image =
//                    cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTERIMAGE))
//                characters.add(character)
//                cursor.moveToNext()
//            }
////            Toast.makeText(context, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
////                .show()
//        }
//        cursor.close()
//        // db.close()
//        return characters
//
//    }
//
//
//    fun dataCheck(): Boolean {
//        val qry = "Select * From $CHARACTER_TABLE_NAME"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(qry, null)
//        var a = 0
//        a = if (cursor.count == 0) {
//            cursor.moveToFirst()
//            while (!cursor.isAfterLast) {
//                Log.d("deceee", "cursor=0")
//                cursor.moveToNext()
//            }
//            cursor.close()
//            1
//        } else {
//            0
//        }
//        cursor.close()
//        db.close()
//        return a == 1
//    }
//
//    fun updateEpisodeName() {
//        val qry = "UPDATE $CHARACTER_TABLE_NAME" +
//                " SET $COLUMN_CHARACTEREPISODENAME = (SELECT $COLUMN_EPISODENAME FROM $EPISODE_TABLE_NAME WHERE $EPISODE_TABLE_NAME.$COLUMN_EPISODELINK = $CHARACTER_TABLE_NAME.$COLUMN_CHARACTEREPISODE)"
//        val db = this.readableDatabase
//        val cursor = db.rawQuery(qry, null)
//        if (cursor.count == 0)
//            cursor.moveToFirst()
//        while (!cursor.isAfterLast) {
//            Log.d("deceee", "cursor=0")
//            cursor.moveToNext()
//        }
//        cursor.close()
//    }


//}